package com.rbailen.covid.controller.transformer;

import com.rbailen.covid.controller.dto.request.CasesByStatusAndCountryRQDTO;
import com.rbailen.covid.controller.dto.response.CasesByStatusAndCountryRSDTO;
import com.rbailen.covid.controller.dto.response.CountryRSDTO;
import com.rbailen.covid.controller.dto.response.GlobalRSDTO;
import com.rbailen.covid.service.dto.input.CasesByStatusAndCountryIDTO;
import com.rbailen.covid.service.dto.output.CasesByStatusAndCountryODTO;
import com.rbailen.covid.service.dto.output.CountryODTO;
import com.rbailen.covid.service.dto.output.GlobalODTO;

/**
 * The Interface CovidControllerTransformer.
 */
public interface CovidControllerTransformer {

  /**
   * To country RSDTO.
   *
   * @param countryODTO the country ODTO
   * @return the country RSDTO
   */
  CountryRSDTO toCountryRSDTO(CountryODTO countryODTO);

  /**
   * To cases by status and country IDTO.
   *
   * @param casesByStatusAndCountryRQDTO the cases by status and country RQDTO
   * @return the cases by status and country IDTO
   */
  CasesByStatusAndCountryIDTO toCasesByStatusAndCountryIDTO(
      CasesByStatusAndCountryRQDTO casesByStatusAndCountryRQDTO);

  /**
   * To cases by status and country RSDTO.
   *
   * @param casesByStatusAndCountryOutput the cases by status and country output
   * @return the cases by status and country RSDTO
   */
  CasesByStatusAndCountryRSDTO toCasesByStatusAndCountryRSDTO(
      CasesByStatusAndCountryODTO casesByStatusAndCountryOutput);

  /**
   * To global RSDTO.
   *
   * @param globalODTO the global ODTO
   * @return the global RSDTO
   */
  GlobalRSDTO toGlobalRSDTO(GlobalODTO globalODTO);

}
