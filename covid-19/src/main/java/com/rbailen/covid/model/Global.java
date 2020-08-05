package com.rbailen.covid.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class Global.
 */
@Document(indexName = "global-index", shards = 1, replicas = 0, refreshInterval = "-1")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Global {

  /** The id. */
  @Id
  private String id;

  /** The total confirmed. */
  private Integer totalConfirmed;

  /** The total deaths. */
  private Integer totalDeaths;

  /** The total recovered. */
  private Integer totalRecovered;

}
