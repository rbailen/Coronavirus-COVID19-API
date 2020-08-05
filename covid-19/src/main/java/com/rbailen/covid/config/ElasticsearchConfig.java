package com.rbailen.covid.config;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import com.rbailen.covid.model.CasesByStatusAndCountry;
import com.rbailen.covid.model.Global;
import com.rbailen.covid.service.CasesByStatusAndCountryService;
import com.rbailen.covid.service.CountryService;
import com.rbailen.covid.service.DataService;
import com.rbailen.covid.service.dto.input.CasesByStatusAndCountryIDTO;
import com.rbailen.covid.service.dto.input.CountryIDTO;
import com.rbailen.covid.service.dto.output.CasesByStatusAndCountryODTO;
import com.rbailen.covid.service.dto.output.CountryODTO;
import com.rbailen.covid.service.transformer.CovidServiceTransformer;
import com.rbailen.covid.type.StatusType;
import com.rbailen.covid.util.Util;

/**
 * The Class ElasticsearchConfig.
 */
@Configuration
@EnableElasticsearchRepositories
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

  /** The data service. */
  @Autowired
  private DataService dataService;

  /** The country service. */
  @Autowired
  private CountryService countryService;

  /** The covid service transformer. */
  @Autowired
  private CovidServiceTransformer covidServiceTransformer;

  /** The cases by status and country service. */
  @Autowired
  private CasesByStatusAndCountryService casesByStatusAndCountryService;

  /**
   * Elasticsearch client.
   *
   * @return the rest high level client
   */
  @Bean
  public RestHighLevelClient elasticsearchClient() {
    final ClientConfiguration clientConfiguration =
        ClientConfiguration.builder().connectedTo("localhost:9200").build();

    return RestClients.create(clientConfiguration).rest();
  }

  /**
   * Elasticsearch template.
   *
   * @return the elasticsearch operations
   */
  @Bean
  public ElasticsearchOperations elasticsearchTemplate() {
    return new ElasticsearchRestTemplate(elasticsearchClient());
  }

  /**
   * Inits the.
   */
  @PostConstruct
  public void init() {
    elasticsearchTemplate().indexOps(CasesByStatusAndCountry.class).delete();
    elasticsearchTemplate().indexOps(Global.class).delete();

    List<CountryODTO> countryODTOList = dataService.getCountriesFromAPI();
    List<CountryIDTO> countryIDTOList = countryODTOList.stream()
        .map(countryODTO -> covidServiceTransformer.toCountryIDTO(countryODTO))
        .collect(Collectors.toList());
    countryService.saveCountries(countryIDTOList);

    String now = Util.localDateTimeToString(LocalDate.now().atStartOfDay());
    String nowSubtractOneDay = Util.subtractDayToDate(now);
    List<CasesByStatusAndCountryODTO> casesByStatusAndCountryODTOList =
        dataService.getCasesByStatusAndCountryFromAPI(CasesByStatusAndCountryIDTO.builder()
            .from(nowSubtractOneDay).to(now).country("spain").status(StatusType.RECOVERED).build(),
            true);
    List<CasesByStatusAndCountryIDTO> casesByStatusAndCountryIDTOList =
        casesByStatusAndCountryODTOList.stream()
            .map(casesByStatusAndCountryODTO -> covidServiceTransformer
                .toCasesByStatusAndCountryIDTO(casesByStatusAndCountryODTO))
            .collect(Collectors.toList());
    casesByStatusAndCountryService.saveCasesByStatusAndCountry(casesByStatusAndCountryIDTOList);
  }

}
