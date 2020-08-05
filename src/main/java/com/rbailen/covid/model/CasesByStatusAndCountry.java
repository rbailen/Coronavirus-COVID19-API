package com.rbailen.covid.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import com.rbailen.covid.type.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class CasesByStatusAndCountry.
 */
@Document(indexName = "status-country-index", shards = 1, replicas = 0, refreshInterval = "-1")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CasesByStatusAndCountry {

  /** The id. */
  @Id
  private String id;

  /** The country. */
  private String country;

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
  @Enumerated(EnumType.STRING)
  private StatusType status;

  /** The date. */
  private String date;

}
