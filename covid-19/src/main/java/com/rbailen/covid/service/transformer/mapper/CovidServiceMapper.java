package com.rbailen.covid.service.transformer.mapper;

import org.mapstruct.Mapper;
import com.rbailen.covid.model.CasesByStatusAndCountry;
import com.rbailen.covid.model.Country;
import com.rbailen.covid.model.Global;
import com.rbailen.covid.service.dto.input.CasesByStatusAndCountryIDTO;
import com.rbailen.covid.service.dto.input.CountryIDTO;
import com.rbailen.covid.service.dto.input.GlobalIDTO;
import com.rbailen.covid.service.dto.output.CasesByStatusAndCountryODTO;
import com.rbailen.covid.service.dto.output.CountryODTO;
import com.rbailen.covid.service.dto.output.GlobalODTO;

/**
 * The Interface CovidServiceMapper.
 */
@Mapper
public interface CovidServiceMapper {

  /**
   * To country ODTO.
   *
   * @param country the country
   * @return the country ODTO
   */
  CountryODTO toCountryODTO(Country country);

  /**
   * To country.
   *
   * @param countryIDTO the country IDTO
   * @return the country
   */
  Country toCountry(CountryIDTO countryIDTO);

  /**
   * To country IDTO.
   *
   * @param countryODTO the country ODTO
   * @return the country IDTO
   */
  CountryIDTO toCountryIDTO(CountryODTO countryODTO);

  /**
   * To cases by status and country ODTO.
   *
   * @param casesByStatusAndCountry the cases by status and country
   * @return the cases by status and country ODTO
   */
  CasesByStatusAndCountryODTO toCasesByStatusAndCountryODTO(
      CasesByStatusAndCountry casesByStatusAndCountry);

  /**
   * To cases by status and country.
   *
   * @param casesByStatusAndCountryODTO the cases by status and country ODTO
   * @return the cases by status and country
   */
  CasesByStatusAndCountry toCasesByStatusAndCountry(
      CasesByStatusAndCountryODTO casesByStatusAndCountryODTO);

  /**
   * To cases by status and country IDTO.
   *
   * @param CcsesByStatusAndCountryODTO the ccses by status and country ODTO
   * @return the cases by status and country IDTO
   */
  CasesByStatusAndCountryIDTO toCasesByStatusAndCountryIDTO(
      CasesByStatusAndCountryODTO CcsesByStatusAndCountryODTO);

  /**
   * To cases by status and country.
   *
   * @param casesByStatusAndCountryIDTO the cases by status and country IDTO
   * @return the cases by status and country
   */
  CasesByStatusAndCountry toCasesByStatusAndCountry(
      CasesByStatusAndCountryIDTO casesByStatusAndCountryIDTO);

  /**
   * To global.
   *
   * @param globalIDTO the global IDTO
   * @return the global
   */
  Global toGlobal(GlobalIDTO globalIDTO);

  /**
   * To global ODTO.
   *
   * @param global the global
   * @return the global ODTO
   */
  GlobalODTO toGlobalODTO(Global global);

  /**
   * To global IDTO.
   *
   * @param global the global
   * @return the global IDTO
   */
  GlobalIDTO toGlobalIDTO(Global global);

  /**
   * To global IDTO.
   *
   * @param globalODTO the global ODTO
   * @return the global IDTO
   */
  GlobalIDTO toGlobalIDTO(GlobalODTO globalODTO);

}
