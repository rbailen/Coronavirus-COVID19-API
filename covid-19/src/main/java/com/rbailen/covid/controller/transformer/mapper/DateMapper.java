package com.rbailen.covid.controller.transformer.mapper;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

/**
 * The Class DateMapper.
 */
@Component
public class DateMapper {

  /** The formatter. */
  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

  /**
   * As string.
   *
   * @param date the date
   * @return the string
   */
  public String asString(OffsetDateTime date) {
    return formatter.format(date);
  }

}
