package com.rbailen.covid.service;

import java.util.List;
import com.rbailen.covid.service.dto.input.CountryIDTO;
import com.rbailen.covid.service.dto.output.CountryODTO;

/**
 * The Interface CountryService.
 */
public interface CountryService {

  /**
   * Gets the countries.
   *
   * @return the countries
   */
  List<CountryODTO> getCountries();

  /**
   * Save countries.
   *
   * @param countries the countries
   */
  void saveCountries(List<CountryIDTO> countries);

  /**
   * Gets the country by slug.
   *
   * @param slug the slug
   * @return the country by slug
   */
  CountryODTO getCountryBySlug(String slug);

}
