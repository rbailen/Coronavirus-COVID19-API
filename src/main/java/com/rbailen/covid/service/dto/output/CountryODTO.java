package com.rbailen.covid.service.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryODTO {

  /** The country. */
  @JsonProperty(value = "Country")
  private String country;

  /** The slug. */
  @JsonProperty(value = "Slug")
  private String slug;

  /** The iso 2. */
  @JsonProperty(value = "ISO2")
  private String iso2;

}
