package com.rbailen.covid.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class Country.
 */
@Entity
@Table(name = "Country")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {

  /** The country. */
  private String country;

  /** The slug. */
  private String slug;

  /** The iso 2. */
  @Id
  private String iso2;

}
