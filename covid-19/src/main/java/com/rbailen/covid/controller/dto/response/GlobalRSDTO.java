package com.rbailen.covid.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlobalRSDTO {

  /** The total confirmed. */
  private Integer totalConfirmed;

  /** The total deaths. */
  private Integer totalDeaths;

  /** The total recovered. */
  private Integer totalRecovered;

}
