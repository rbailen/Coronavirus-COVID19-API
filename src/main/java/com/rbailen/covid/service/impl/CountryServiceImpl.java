package com.rbailen.covid.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbailen.covid.model.Country;
import com.rbailen.covid.repository.CountryRepository;
import com.rbailen.covid.service.CountryService;
import com.rbailen.covid.service.dto.input.CountryIDTO;
import com.rbailen.covid.service.dto.output.CountryODTO;
import com.rbailen.covid.service.transformer.CovidServiceTransformer;

/**
 * The Class CountryServiceImpl.
 */
@Service
public class CountryServiceImpl implements CountryService {

  /** The country repository. */
  @Autowired
  private CountryRepository countryRepository;

  /** The covid service transformer. */
  @Autowired
  private CovidServiceTransformer covidServiceTransformer;

  /**
   * Gets the countries.
   *
   * @return the countries
   */
  @Override
  public List<CountryODTO> getCountries() {
    List<Country> countries = countryRepository.findAll();
    return countries.stream().map(country -> covidServiceTransformer.toCountryODTO(country))
        .collect(Collectors.toList());
  }

  /**
   * Save countries.
   *
   * @param countries the countries
   */
  @Override
  public void saveCountries(List<CountryIDTO> countries) {
    List<Country> countryList =
        countries.stream().map(countryIDTO -> covidServiceTransformer.toCountry(countryIDTO))
            .collect(Collectors.toList());
    countryRepository.saveAll(countryList);
  }

  /**
   * Gets the country by slug.
   *
   * @param slug the slug
   * @return the country by slug
   */
  @Override
  public CountryODTO getCountryBySlug(String slug) {
    Country country = countryRepository.findBySlug(slug);
    return covidServiceTransformer.toCountryODTO(country);
  }

}
