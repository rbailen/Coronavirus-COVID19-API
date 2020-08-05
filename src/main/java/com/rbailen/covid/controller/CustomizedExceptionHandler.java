package com.rbailen.covid.controller;

import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.rbailen.covid.exception.CountryNotFoundException;
import com.rbailen.covid.exception.ExceptionResponse;

/**
 * The Class CustomizedExceptionHandler.
 */
@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Handle all exceptions.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handle country not found exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(CountryNotFoundException.class)
  public final ResponseEntity<Object> handleCountryNotFoundException(CountryNotFoundException ex,
      WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
  }


  /**
   * Handle method argument type mismatch.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
      MethodArgumentTypeMismatchException ex, WebRequest request) {
    String error = ex.getName() + " field should be of type " + ex.getRequiredType().getName();

    ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), "Validation Failed", error);

    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle missing servlet request parameter.
   *
   * @param ex the ex
   * @param headers the headers
   * @param status the status
   * @param request the request
   * @return the response entity
   */
  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    String error = ex.getParameterName() + " parameter is missing";

    ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), "Validation Failed", error);

    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

}
