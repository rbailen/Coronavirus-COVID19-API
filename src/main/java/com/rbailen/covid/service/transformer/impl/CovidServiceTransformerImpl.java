package com.rbailen.covid.service.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.rbailen.covid.model.CasesByStatusAndCountry;
import com.rbailen.covid.model.Country;
import com.rbailen.covid.model.Global;
import com.rbailen.covid.service.dto.input.CasesByStatusAndCountryIDTO;
import com.rbailen.covid.service.dto.input.CountryIDTO;
import com.rbailen.covid.service.dto.input.GlobalIDTO;
import com.rbailen.covid.service.dto.output.CasesByStatusAndCountryODTO;
import com.rbailen.covid.service.dto.output.CountryODTO;
import com.rbailen.covid.service.dto.output.GlobalODTO;
import com.rbailen.covid.service.transformer.CovidServiceTransformer;
import com.rbailen.covid.service.transformer.mapper.CovidServiceMapper;

/**
 * The Class CovidServiceTransformerImpl.
 */
@Component
public class CovidServiceTransformerImpl implements CovidServiceTransformer {

  /** The covid service mapper. */
  @Autowired
  private CovidServiceMapper covidServiceMapper;

  /**
   * To country ODTO.
   *
   * @param country the country
   * @return the country ODTO
   */
  @Override
  public CountryODTO toCountryODTO(Country country) {
    return covidServiceMapper.toCountryODTO(country);
  }

  /**
   * To country.
   *
   * @param countryIDTO the country IDTO
   * @return the country
   */
  @Override
  public Country toCountry(CountryIDTO countryIDTO) {
    return covidServiceMapper.toCountry(countryIDTO);
  }

  /**
   * To country IDTO.
   *
   * @param countryODTO the country ODTO
   * @return the country IDTO
   */
  @Override
  public CountryIDTO toCountryIDTO(CountryODTO countryODTO) {
    return covidServiceMapper.toCountryIDTO(countryODTO);
  }

  /**
   * To cases by status and country ODTO.
   *
   * @param casesByStatusAndCountry the cases by status and country
   * @return the cases by status and country ODTO
   */
  @Override
  public CasesByStatusAndCountryODTO toCasesByStatusAndCountryODTO(
      CasesByStatusAndCountry casesByStatusAndCountry) {
    return covidServiceMapper.toCasesByStatusAndCountryODTO(casesByStatusAndCountry);
  }

  /**
   * To cases by status and country.
   *
   * @param casesByStatusAndCountryODTO the cases by status and country ODTO
   * @return the cases by status and country
   */
  @Override
  public CasesByStatusAndCountry toCasesByStatusAndCountry(
      CasesByStatusAndCountryODTO casesByStatusAndCountryODTO) {
    return covidServiceMapper.toCasesByStatusAndCountry(casesByStatusAndCountryODTO);
  }

  /**
   * To cases by status and country IDTO.
   *
   * @param CcsesByStatusAndCountryODTO the ccses by status and country ODTO
   * @return the cases by status and country IDTO
   */
  @Override
  public CasesByStatusAndCountryIDTO toCasesByStatusAndCountryIDTO(
      CasesByStatusAndCountryODTO CcsesByStatusAndCountryODTO) {
    return covidServiceMapper.toCasesByStatusAndCountryIDTO(CcsesByStatusAndCountryODTO);
  }

  /**
   * To cases by status and country.
   *
   * @param casesByStatusAndCountryIDTO the cases by status and country IDTO
   * @return the cases by status and country
   */
  @Override
  public CasesByStatusAndCountry toCasesByStatusAndCountry(
      CasesByStatusAndCountryIDTO casesByStatusAndCountryIDTO) {
    return covidServiceMapper.toCasesByStatusAndCountry(casesByStatusAndCountryIDTO);
  }

  /**
   * To global.
   *
   * @param globalIDTO the global IDTO
   * @return the global
   */
  @Override
  public Global toGlobal(GlobalIDTO globalIDTO) {
    return covidServiceMapper.toGlobal(globalIDTO);
  }

  /**
   * To global ODTO.
   *
   * @param global the global
   * @return the global ODTO
   */
  @Override
  public GlobalODTO toGlobalODTO(Global global) {
    return covidServiceMapper.toGlobalODTO(global);
  }

  /**
   * To global IDTO.
   *
   * @param globalODTO the global ODTO
   * @return the global IDTO
   */
  @Override
  public GlobalIDTO toGlobalIDTO(GlobalODTO globalODTO) {
    return covidServiceMapper.toGlobalIDTO(globalODTO);
  }

}
