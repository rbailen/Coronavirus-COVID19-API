package com.rbailen.covid.service.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryIDTO {

  /** The country. */
  private String country;

  /** The slug. */
  private String slug;

  /** The iso 2. */
  private String iso2;

}
