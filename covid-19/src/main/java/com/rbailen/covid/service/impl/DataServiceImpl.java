package com.rbailen.covid.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.rbailen.covid.service.DataService;
import com.rbailen.covid.service.dto.input.CasesByStatusAndCountryIDTO;
import com.rbailen.covid.service.dto.output.CasesByStatusAndCountryODTO;
import com.rbailen.covid.service.dto.output.CountryODTO;
import com.rbailen.covid.service.dto.output.GlobalODTO;

/**
 * The Class DataServiceImpl.
 */
@Service
public class DataServiceImpl implements DataService {

  /** The rest template. */
  private RestTemplate restTemplate = new RestTemplate();

  /** The uri country. */
  @Value("${covid.service.country-url}")
  private String uriCountry;

  /** The uri summary. */
  @Value("${covid.service.summary-url}")
  private String uriSummary;

  /** The uri world total. */
  @Value("${covid.service.world-total-url}")
  private String uriWorldTotal;

  /** The uri status country. */
  @Value("${covid.service.status-country-url}")
  private String uriStatusCountry;

  /**
   * Gets the countries from API.
   *
   * @return the countries from API
   */
  @Override
  public List<CountryODTO> getCountriesFromAPI() {
    ResponseEntity<List<CountryODTO>> responseEntity = restTemplate.exchange(uriCountry,
        HttpMethod.GET, null, new ParameterizedTypeReference<List<CountryODTO>>() {});

    List<CountryODTO> countries = responseEntity.getBody();

    return countries;
  }

  /**
   * Gets the world total from API.
   *
   * @return the world total from API
   */
  @Override
  public GlobalODTO getWorldTotalFromAPI() {
    ResponseEntity<GlobalODTO> responseEntity = restTemplate.exchange(uriWorldTotal, HttpMethod.GET,
        null, new ParameterizedTypeReference<GlobalODTO>() {});

    GlobalODTO globalODTO = responseEntity.getBody();

    return globalODTO;
  }

  /**
   * Gets the cases by status and country from API.
   *
   * @param casesByStatusAndCountryIDTO the cases by status and country IDTO
   * @param equalInputDates the equal input dates
   * @return the cases by status and country from API
   */
  @Override
  public List<CasesByStatusAndCountryODTO> getCasesByStatusAndCountryFromAPI(
      CasesByStatusAndCountryIDTO casesByStatusAndCountryIDTO, boolean equalInputDates) {
    ResponseEntity<List<CasesByStatusAndCountryODTO>> responseEntity =
        restTemplate.exchange(uriStatusCountry, HttpMethod.GET, null,
            new ParameterizedTypeReference<List<CasesByStatusAndCountryODTO>>() {},
            casesByStatusAndCountryIDTO.getCountry(), casesByStatusAndCountryIDTO.getStatus().value,
            casesByStatusAndCountryIDTO.getFrom(), casesByStatusAndCountryIDTO.getTo());

    List<CasesByStatusAndCountryODTO> casesByStatusAndCountryODTOList = responseEntity.getBody();
    if (equalInputDates) {
      CasesByStatusAndCountryODTO firstElementODTO =
          casesByStatusAndCountryODTOList.stream().findFirst().orElse(null);
      casesByStatusAndCountryODTOList.clear();
      casesByStatusAndCountryODTOList.add(firstElementODTO);
    }

    return casesByStatusAndCountryODTOList;
  }

}
