package com.mercateo.exception;

/**
 *
 *
 * <h1>InvalidTokenException</h1>
 *
 * <p>This exception is thrown by the parser
 */
public class InvalidTokenException extends RuntimeException {

  public InvalidTokenException(String message) {
    super(message);
  }
}
