package com.rbailen.covid.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import com.rbailen.covid.exception.DateException;
import com.rbailen.covid.model.CasesByStatusAndCountry;
import com.rbailen.covid.repository.CasesByStatusAndCountryRepository;
import com.rbailen.covid.service.CasesByStatusAndCountryService;
import com.rbailen.covid.service.DataService;
import com.rbailen.covid.service.dto.input.CasesByStatusAndCountryIDTO;
import com.rbailen.covid.service.dto.output.CasesByStatusAndCountryODTO;
import com.rbailen.covid.service.transformer.CovidServiceTransformer;
import com.rbailen.covid.util.Util;

/**
 * The Class CasesByStatusAndCountryServiceImpl.
 */
@Service
public class CasesByStatusAndCountryServiceImpl implements CasesByStatusAndCountryService {

  /** The data service. */
  @Autowired
  private DataService dataService;

  /** The cases by status and country repository. */
  @Autowired
  private CasesByStatusAndCountryRepository casesByStatusAndCountryRepository;

  /** The elasticsearch template. */
  @Autowired
  private ElasticsearchRestTemplate elasticsearchTemplate;

  /** The covid service transformer. */
  @Autowired
  private CovidServiceTransformer covidServiceTransformer;

  /**
   * Gets the cases by status and country.
   *
   * @param casesByStatusAndCountryIDTO the cases by status and country IDTO
   * @return the cases by status and country
   */
  @Override
  public Iterable<CasesByStatusAndCountryODTO> getCasesByStatusAndCountry(
      CasesByStatusAndCountryIDTO casesByStatusAndCountryIDTO) {

    this.checkDates(casesByStatusAndCountryIDTO);

    List<CasesByStatusAndCountryODTO> casesFoundList =
        this.findByStatusAndCountry(casesByStatusAndCountryIDTO);

    boolean equalInputDates = this.equalInputDates(casesByStatusAndCountryIDTO);

    List<CasesByStatusAndCountryODTO> casesByStatusAndCountryODTOList =
        dataService.getCasesByStatusAndCountryFromAPI(casesByStatusAndCountryIDTO, equalInputDates);

    if (casesFoundList.size() == casesByStatusAndCountryODTOList.size()) {
      return casesFoundList;
    }

    List<CasesByStatusAndCountryODTO> newCasesList = new ArrayList<>();
    if (casesFoundList.isEmpty()) {
      casesByStatusAndCountryODTOList.forEach(newCasesList::add);
    } else {
      HashSet<CasesByStatusAndCountryODTO> casesFoundHashSet = new HashSet<>(casesFoundList);
      casesByStatusAndCountryODTOList.forEach(casesByStatusAndCountryODTO -> {
        if (!casesFoundHashSet.contains(casesByStatusAndCountryODTO)) {
          newCasesList.add(casesByStatusAndCountryODTO);
        }
      });
    }

    List<CasesByStatusAndCountryIDTO> casesByStatusAndCountryIDTOList = newCasesList.stream()
        .map(casesByStatusAndCountryODTO -> covidServiceTransformer
            .toCasesByStatusAndCountryIDTO(casesByStatusAndCountryODTO))
        .collect(Collectors.toList());

    this.controlSavedCases(equalInputDates, casesByStatusAndCountryIDTOList);
    this.saveCasesByStatusAndCountry(casesByStatusAndCountryIDTOList);

    return casesByStatusAndCountryODTOList;
  }

  /**
   * Find by status and country.
   *
   * @param casesByStatusAndCountryIDTO the cases by status and country IDTO
   * @return the list
   */
  private List<CasesByStatusAndCountryODTO> findByStatusAndCountry(
      CasesByStatusAndCountryIDTO casesByStatusAndCountryIDTO) {
    QueryBuilder queryBuilder = QueryBuilders.boolQuery()
        .must(QueryBuilders.rangeQuery("date").gte(casesByStatusAndCountryIDTO.getFrom())
            .lte(casesByStatusAndCountryIDTO.getTo()))
        .must(QueryBuilders.matchQuery("country", casesByStatusAndCountryIDTO.getCountry()))
        .must(QueryBuilders.matchQuery("status", casesByStatusAndCountryIDTO.getStatus()));

    final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder)
        .withSort(SortBuilders.fieldSort("date").order(SortOrder.ASC)).build();
    final SearchHits<CasesByStatusAndCountry> searchHits = elasticsearchTemplate.search(searchQuery,
        CasesByStatusAndCountry.class, IndexCoordinates.of("status-country-index"));
    List<CasesByStatusAndCountry> cases = new ArrayList<>();
    searchHits.getSearchHits().forEach(searchHit -> cases.add(searchHit.getContent()));

    return cases.stream().map(casesByStatusAndCountry -> covidServiceTransformer
        .toCasesByStatusAndCountryODTO(casesByStatusAndCountry)).collect(Collectors.toList());
  }

  /**
   * Save cases by status and country.
   *
   * @param casesByStatusAndCountryIDTOList the cases by status and country IDTO list
   */
  public void saveCasesByStatusAndCountry(
      List<CasesByStatusAndCountryIDTO> casesByStatusAndCountryIDTOList) {
    List<CasesByStatusAndCountry> casesByStatusAndCountryList =
        casesByStatusAndCountryIDTOList.stream()
            .map(casesByStatusAndCountryIDTO -> covidServiceTransformer
                .toCasesByStatusAndCountry(casesByStatusAndCountryIDTO))
            .collect(Collectors.toList());
    casesByStatusAndCountryRepository.saveAll(casesByStatusAndCountryList);
  }

  /**
   * Check dates.
   *
   * @param casesByStatusAndCountryIDTO the cases by status and country IDTO
   */
  private void checkDates(CasesByStatusAndCountryIDTO casesByStatusAndCountryIDTO) {
    LocalDateTime now = LocalDate.now().atStartOfDay();
    LocalDateTime fromLDT = Util.stringToLocalDateTime(casesByStatusAndCountryIDTO.getFrom());
    LocalDateTime toLDT = Util.stringToLocalDateTime(casesByStatusAndCountryIDTO.getTo());

    if (fromLDT.isAfter(toLDT)) {
      throw new DateException(fromLDT + " is after than " + toLDT);
    }

    if ((now.isBefore(toLDT)) || (now.equals(fromLDT.toLocalDate().atStartOfDay())
        && now.equals(toLDT.toLocalDate().atStartOfDay()))) {
      throw new DateException("No data indexed for the current day");
    }

    casesByStatusAndCountryIDTO
        .setFrom(Util.localDateTimeToString(fromLDT.toLocalDate().atStartOfDay()));
    casesByStatusAndCountryIDTO
        .setTo(Util.localDateTimeToString(toLDT.toLocalDate().atStartOfDay()));
  }

  /**
   * Equal input dates.
   *
   * @param casesByStatusAndCountryIDTO the cases by status and country IDTO
   * @return true, if successful
   */
  private boolean equalInputDates(CasesByStatusAndCountryIDTO casesByStatusAndCountryIDTO) {
    if (casesByStatusAndCountryIDTO.getFrom().equals(casesByStatusAndCountryIDTO.getTo())) {
      casesByStatusAndCountryIDTO.setTo(Util.addDayToDate(casesByStatusAndCountryIDTO.getTo()));
      return true;
    }
    return false;
  }

  /**
   * Control saved cases.
   *
   * @param equalInputDates the equal input dates
   * @param casesByStatusAndCountryIDTOList the cases by status and country IDTO list
   */
  private void controlSavedCases(boolean equalInputDates,
      List<CasesByStatusAndCountryIDTO> casesByStatusAndCountryIDTOList) {
    if (equalInputDates) {
      CasesByStatusAndCountryIDTO firstElementIDTO =
          casesByStatusAndCountryIDTOList.stream().findFirst().orElse(null);
      casesByStatusAndCountryIDTOList.clear();
      casesByStatusAndCountryIDTOList.add(firstElementIDTO);
    }
  }

}
