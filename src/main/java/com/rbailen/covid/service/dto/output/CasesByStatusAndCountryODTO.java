package com.rbailen.covid.service.dto.output;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rbailen.covid.type.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CasesByStatusAndCountryODTO {

  /** The country. */
  @JsonProperty(value = "Country")
  private String country;

  /** The country code. */
  @JsonProperty(value = "CountryCode")
  private String countryCode;

  /** The province. */
  @JsonProperty(value = "Province")
  private String province;

  /** The city. */
  @JsonProperty(value = "City")
  private String city;

  /** The city code. */
  @JsonProperty(value = "CityCode")
  private String cityCode;

  /** The lat. */
  @JsonProperty(value = "Lat")
  private String lat;

  /** The lon. */
  @JsonProperty(value = "Lon")
  private String lon;

  /** The cases. */
  @JsonProperty(value = "Cases")
  private Integer cases;

  /** The status. */
  @JsonProperty(value = "Status")
  @Enumerated(EnumType.STRING)
  private StatusType status;

  /** The date. */
  @JsonProperty(value = "Date")
  private String date;

}
