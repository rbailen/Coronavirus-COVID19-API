package com.rbailen.covid.service;

import java.util.List;
import com.rbailen.covid.service.dto.input.CasesByStatusAndCountryIDTO;
import com.rbailen.covid.service.dto.output.CasesByStatusAndCountryODTO;

/**
 * The Interface CasesByStatusAndCountryService.
 */
public interface CasesByStatusAndCountryService {

  /**
   * Gets the cases by status and country.
   *
   * @param casesByStatusAndCountryIDTO the cases by status and country IDTO
   * @return the cases by status and country
   */
  Iterable<CasesByStatusAndCountryODTO> getCasesByStatusAndCountry(
      CasesByStatusAndCountryIDTO casesByStatusAndCountryIDTO);

  /**
   * Save cases by status and country.
   *
   * @param casesByStatusAndCountryIDTOList the cases by status and country IDTO list
   * @param equalInputDates the equal input dates
   */
  public void saveCasesByStatusAndCountry(
      List<CasesByStatusAndCountryIDTO> casesByStatusAndCountryIDTOList);

}
