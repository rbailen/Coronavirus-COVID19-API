package com.rbailen.covid.service.transformer;

import com.rbailen.covid.model.CasesByStatusAndCountry;
import com.rbailen.covid.model.Country;
import com.rbailen.covid.model.Global;
import com.rbailen.covid.service.dto.input.CasesByStatusAndCountryIDTO;
import com.rbailen.covid.service.dto.input.CountryIDTO;
import com.rbailen.covid.service.dto.input.GlobalIDTO;
import com.rbailen.covid.service.dto.output.CasesByStatusAndCountryODTO;
import com.rbailen.covid.service.dto.output.CountryODTO;
import com.rbailen.covid.service.dto.output.GlobalODTO;

public interface CovidServiceTransformer {

  CountryODTO toCountryODTO(Country country);

  Country toCountry(CountryIDTO countryIDTO);

  CountryIDTO toCountryIDTO(CountryODTO countryODTO);

  CasesByStatusAndCountryODTO toCasesByStatusAndCountryODTO(
      CasesByStatusAndCountry casesByStatusAndCountry);

  CasesByStatusAndCountry toCasesByStatusAndCountry(
      CasesByStatusAndCountryODTO casesByStatusAndCountryODTO);

  CasesByStatusAndCountryIDTO toCasesByStatusAndCountryIDTO(
      CasesByStatusAndCountryODTO CcsesByStatusAndCountryODTO);

  CasesByStatusAndCountry toCasesByStatusAndCountry(
      CasesByStatusAndCountryIDTO casesByStatusAndCountryIDTO);

  Global toGlobal(GlobalIDTO globalIDTO);

  GlobalODTO toGlobalODTO(Global global);

  GlobalIDTO toGlobalIDTO(GlobalODTO globalODTO);

}
