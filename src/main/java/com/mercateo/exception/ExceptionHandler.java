package com.mercateo.exception;

/**
 *
 *
 * <h1>ExceptionHandler</h1>
 *
 * <p>Global exception handler.
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

  /** prints exception in teh console */
  @Override
  public void uncaughtException(final Thread thread, final Throwable error) {
    System.out.println("caught global exception " + thread + " - " + error);
  }
}
