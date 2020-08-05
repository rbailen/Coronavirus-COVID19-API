package com.rbailen.covid.service.dto.input;

import com.rbailen.covid.type.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CasesByStatusAndCountryIDTO {

  /** The country. */
  private String country;

  /** The from. */
  private String from;

  /** The to. */
  private String to;

  /** The country code. */
  private String countryCode;

  /** The province. */
  private String province;

  /** The city. */
  private String city;

  /** The city code. */
  private String cityCode;

  /** The lat. */
  private String lat;

  /** The lon. */
  private String lon;

  /** The cases. */
  private Integer cases;

  /** The status. */
  private StatusType status;

  /** The date. */
  private String date;

}
