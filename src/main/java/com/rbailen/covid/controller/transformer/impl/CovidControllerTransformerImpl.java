package com.rbailen.covid.controller.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.rbailen.covid.controller.dto.request.CasesByStatusAndCountryRQDTO;
import com.rbailen.covid.controller.dto.response.CasesByStatusAndCountryRSDTO;
import com.rbailen.covid.controller.dto.response.CountryRSDTO;
import com.rbailen.covid.controller.dto.response.GlobalRSDTO;
import com.rbailen.covid.controller.transformer.CovidControllerTransformer;
import com.rbailen.covid.controller.transformer.mapper.CovidControllerMapper;
import com.rbailen.covid.exception.CountryNotFoundException;
import com.rbailen.covid.service.CountryService;
import com.rbailen.covid.service.dto.input.CasesByStatusAndCountryIDTO;
import com.rbailen.covid.service.dto.output.CasesByStatusAndCountryODTO;
import com.rbailen.covid.service.dto.output.CountryODTO;
import com.rbailen.covid.service.dto.output.GlobalODTO;

/**
 * The Class CovidControllerTransformerImpl.
 */
@Component
public class CovidControllerTransformerImpl implements CovidControllerTransformer {

  /** The covid controller mapper. */
  @Autowired
  private CovidControllerMapper covidControllerMapper;

  /** The country service. */
  @Autowired
  private CountryService countryService;

  /**
   * To country RSDTO.
   *
   * @param countryODTO the country ODTO
   * @return the country RSDTO
   */
  @Override
  public CountryRSDTO toCountryRSDTO(CountryODTO countryODTO) {
    return covidControllerMapper.toCountryRSDTO(countryODTO);
  }

  /**
   * To cases by status and country IDTO.
   *
   * @param casesByStatusAndCountryRQDTO the cases by status and country RQDTO
   * @return the cases by status and country IDTO
   */
  @Override
  public CasesByStatusAndCountryIDTO toCasesByStatusAndCountryIDTO(
      CasesByStatusAndCountryRQDTO casesByStatusAndCountryRQDTO) {
    if (null == countryService.getCountryBySlug(casesByStatusAndCountryRQDTO.getCountry())) {
      throw new CountryNotFoundException(
          "No country found for slug " + casesByStatusAndCountryRQDTO.getCountry());
    }
    return covidControllerMapper.toCasesByStatusAndCountryInput(casesByStatusAndCountryRQDTO);
  }

  /**
   * To cases by status and country RSDTO.
   *
   * @param casesByStatusAndCountryOutput the cases by status and country output
   * @return the cases by status and country RSDTO
   */
  @Override
  public CasesByStatusAndCountryRSDTO toCasesByStatusAndCountryRSDTO(
      CasesByStatusAndCountryODTO casesByStatusAndCountryOutput) {
    return covidControllerMapper.toCasesByStatusAndCountryRSDTO(casesByStatusAndCountryOutput);
  }

  /**
   * To global RSDTO.
   *
   * @param globalODTO the global ODTO
   * @return the global RSDTO
   */
  @Override
  public GlobalRSDTO toGlobalRSDTO(GlobalODTO globalODTO) {
    return covidControllerMapper.toGlobalRSDTO(globalODTO);
  }

}
