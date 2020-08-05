package com.rbailen.covid.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;;

/**
 * The Class CountryNotFoundException.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CountryNotFoundException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new country not found exception.
   *
   * @param message the message
   */
  public CountryNotFoundException(String message) {
    super(message);
  }

}
