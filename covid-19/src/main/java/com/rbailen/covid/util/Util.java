package com.rbailen.covid.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Class Util.
 */
public class Util {

  /**
   * Adds the day to date.
   *
   * @param date the date
   * @return the string
   */
  public static String addDayToDate(String date) {
    LocalDateTime localDateTime = stringToLocalDateTime(date).plusDays(1);
    return localDateTimeToString(localDateTime);
  }

  /**
   * Subtract day to date.
   *
   * @param date the date
   * @return the string
   */
  public static String subtractDayToDate(String date) {
    LocalDateTime localDateTime = stringToLocalDateTime(date).minusDays(1);
    return localDateTimeToString(localDateTime);
  }

  /**
   * String to local date time.
   *
   * @param date the date
   * @return the local date time
   */
  public static LocalDateTime stringToLocalDateTime(String date) {
    DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
    ZonedDateTime zdt = ZonedDateTime.parse(date, dtf);
    return zdt.toLocalDateTime();
  }

  /**
   * Local date time to string.
   *
   * @param date the date
   * @return the string
   */
  public static String localDateTimeToString(LocalDateTime date) {
    Instant instant = date.toInstant(ZoneOffset.UTC);
    return instant.toString();
  }
}
