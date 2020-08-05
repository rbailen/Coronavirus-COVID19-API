package com.rbailen.covid.controller;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.rbailen.covid.controller.dto.request.CasesByStatusAndCountryRQDTO;
import com.rbailen.covid.controller.dto.response.CasesByStatusAndCountryRSDTO;
import com.rbailen.covid.controller.dto.response.CountryRSDTO;
import com.rbailen.covid.controller.dto.response.GlobalRSDTO;
import com.rbailen.covid.controller.transformer.CovidControllerTransformer;
import com.rbailen.covid.exception.ExceptionResponse;
import com.rbailen.covid.service.CasesByStatusAndCountryService;
import com.rbailen.covid.service.CountryService;
import com.rbailen.covid.service.GlobalService;
import com.rbailen.covid.service.dto.input.CasesByStatusAndCountryIDTO;
import com.rbailen.covid.service.dto.output.CasesByStatusAndCountryODTO;
import com.rbailen.covid.service.dto.output.CountryODTO;
import com.rbailen.covid.service.dto.output.GlobalODTO;
import com.rbailen.covid.type.StatusType;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Class CovidController.
 */
@RestController
@RequestMapping("/v1")
@Validated
public class CovidController {

  /** The Constant LOG. */
  private static final Logger LOG = LoggerFactory.getLogger(CovidController.class);

  /** The Constant COVID_ROUTE. */
  public static final String COVID_ROUTE = "/covid";

  /** The country service. */
  @Autowired
  private CountryService countryService;

  /** The global service. */
  @Autowired
  private GlobalService globalService;

  /** The cases by status and country service. */
  @Autowired
  private CasesByStatusAndCountryService casesByStatusAndCountryService;

  /** The covid controller transformer. */
  @Autowired
  private CovidControllerTransformer covidControllerTransformer;

  /**
   * Gets the countries.
   *
   * @return the countries
   */
  @ApiOperation(value = "Return all the available countries",
      notes = "Returning all the available countries and provinces, as well as the country slug for per country requests")
  @ApiResponses({@ApiResponse(code = 200, message = "OK", response = CountryRSDTO[].class)})
  @GetMapping(value = COVID_ROUTE + "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(value = HttpStatus.OK)
  public List<CountryRSDTO> getCountries() {
    LOG.debug("Requesting all the available countries");
    List<CountryODTO> countries = countryService.getCountries();
    LOG.debug("Countries have been obtained: {}", countries);
    return countries.stream()
        .map(countryODTO -> covidControllerTransformer.toCountryRSDTO(countryODTO))
        .collect(Collectors.toList());
  }

  /**
   * Gets the world total.
   *
   * @return the world total
   */
  @ApiOperation(value = "Return the number of cases by status type in the world",
      notes = "Returning the number of cases by status type in the world")
  @ApiResponses({@ApiResponse(code = 200, message = "OK", response = GlobalRSDTO.class)})
  @GetMapping(value = COVID_ROUTE + "/world/total", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(value = HttpStatus.OK)
  public GlobalRSDTO getWorldTotal() {
    LOG.debug("Requesting all live cases by status type in the world");
    GlobalODTO globalODTO = globalService.getWorldTotal();
    LOG.debug("Live cases have been obtained: {}", globalODTO);
    return covidControllerTransformer.toGlobalRSDTO(globalODTO);
  }

  /**
   * Gets the cases by status and country.
   *
   * @param country the country
   * @param status the status
   * @param from the from
   * @param to the to
   * @return the cases by status and country
   */
  @ApiOperation(value = "Return all cases by status type for a country between a given date",
      notes = "Returning all cases by status type for a country between a given date. Country must be the slug from "
          + COVID_ROUTE + " /countries. " + "Cases must be one of: CONFIRMED, RECOVERED, DEATHS")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK", response = CasesByStatusAndCountryRSDTO[].class),
      @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
      @ApiResponse(code = 404, message = "Not Found", response = ExceptionResponse.class),
      @ApiResponse(code = 500, message = "Internal Server Error",
          response = ExceptionResponse.class)})
  @GetMapping(value = COVID_ROUTE + "/country" + "/{country}" + "/status" + "/{status}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(value = HttpStatus.OK)
  public List<CasesByStatusAndCountryRSDTO> getCasesByStatusAndCountry(
      @ApiParam(required = true, name = "country", value = "The country of the case",
          example = "spain") @PathVariable(name = "country",
              required = true) @Pattern(regexp = "[a-zA-Z]+") String country,
      @ApiParam(required = true, name = "status", value = "The status type of the case",
          example = "CONFIRMED") @PathVariable(name = "status", required = true) StatusType status,
      @ApiParam(required = true, name = "from", value = "Init date",
          example = "2020-07-01T00:00:00Z") @RequestParam(name = "from",
              required = true) @DateTimeFormat(
                  iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime from,
      @ApiParam(required = true, name = "to", value = "End date",
          example = "2020-07-01T00:00:00Z") @RequestParam(name = "to",
              required = true) @DateTimeFormat(
                  iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime to) {
    LOG.info("Requesting all cases from {} with status type {} between {} and {}", country, status,
        from, to);
    CasesByStatusAndCountryRQDTO casesByStatusAndCountryRQDTO = CasesByStatusAndCountryRQDTO
        .builder().country(country).status(status).from(from).to(to).build();
    CasesByStatusAndCountryIDTO casesByStatusAndCountryIDTO =
        covidControllerTransformer.toCasesByStatusAndCountryIDTO(casesByStatusAndCountryRQDTO);

    Iterable<CasesByStatusAndCountryODTO> casesByStatusAndCountryODTOIterable =
        casesByStatusAndCountryService.getCasesByStatusAndCountry(casesByStatusAndCountryIDTO);

    List<CasesByStatusAndCountryODTO> casesByStatusAndCountryODTOList = new ArrayList<>();
    casesByStatusAndCountryODTOIterable.forEach(casesByStatusAndCountryODTOList::add);
    LOG.info("Cases from {} with status type {} between {} and {} have been obtained", country,
        status, from, to);
    return casesByStatusAndCountryODTOList.stream()
        .map(casesByStatusAndCountryODTO -> covidControllerTransformer
            .toCasesByStatusAndCountryRSDTO(casesByStatusAndCountryODTO))
        .collect(Collectors.toList());
  }

}
