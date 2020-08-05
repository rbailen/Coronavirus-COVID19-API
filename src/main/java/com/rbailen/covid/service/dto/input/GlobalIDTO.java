package com.rbailen.covid.service.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlobalIDTO {

  /** The total confirmed. */
  private Integer totalConfirmed;

  /** The total deaths. */
  private Integer totalDeaths;

  /** The total recovered. */
  private Integer totalRecovered;

}
