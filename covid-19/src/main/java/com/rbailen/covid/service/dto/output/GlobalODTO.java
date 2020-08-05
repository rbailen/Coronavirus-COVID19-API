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
public class GlobalODTO {

  /** The total confirmed. */
  @JsonProperty(value = "TotalConfirmed")
  private Integer totalConfirmed;

  /** The total deaths. */
  @JsonProperty(value = "TotalDeaths")
  private Integer totalDeaths;

  /** The total recovered. */
  @JsonProperty(value = "TotalRecovered")
  private Integer totalRecovered;

}
