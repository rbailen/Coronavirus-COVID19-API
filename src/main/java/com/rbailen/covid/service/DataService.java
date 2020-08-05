package com.rbailen.covid.service;

import java.util.List;
import com.rbailen.covid.service.dto.input.CasesByStatusAndCountryIDTO;
import com.rbailen.covid.service.dto.output.CasesByStatusAndCountryODTO;
import com.rbailen.covid.service.dto.output.CountryODTO;
import com.rbailen.covid.service.dto.output.GlobalODTO;

/**
 * The Interface DataService.
 */
public interface DataService {

  /**
   * Gets the countries from API.
   *
   * @return the countries from API
   */
  List<CountryODTO> getCountriesFromAPI();

  /**
   * Gets the world total from API.
   *
   * @return the world total from API
   */
  GlobalODTO getWorldTotalFromAPI();

  /**
   * Gets the cases by status and country from API.
   *
   * @param casesByStatusAndCountryIDTO the cases by status and country IDTO
   * @param equalInputDates the equal input dates
   * @return the cases by status and country from API
   */
  List<CasesByStatusAndCountryODTO> getCasesByStatusAndCountryFromAPI(
      CasesByStatusAndCountryIDTO casesByStatusAndCountryIDTO, boolean equalInputDates);

}
