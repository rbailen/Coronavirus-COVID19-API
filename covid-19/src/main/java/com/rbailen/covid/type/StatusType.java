package com.rbailen.covid.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Enum StatusType.
 */
public enum StatusType {
  
  /** The confirmed. */
  @JsonProperty("confirmed")
  CONFIRMED("confirmed"),

  /** The recovered. */
  @JsonProperty("recovered")
  RECOVERED("recovered"),

  /** The deaths. */
  @JsonProperty("deaths")
  DEATHS("deaths");

  /**
   * Instantiates a new status type.
   *
   * @param value the value
   */
  private StatusType(String value) {
    this.value = value;
  }

  /** The value. */
  public final String value;

}
