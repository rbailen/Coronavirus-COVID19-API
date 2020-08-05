package com.rbailen.covid.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryRSDTO {

  /** The country. */
  private String country;

  /** The slug. */
  private String slug;

  /** The iso 2. */
  private String iso2;

}
