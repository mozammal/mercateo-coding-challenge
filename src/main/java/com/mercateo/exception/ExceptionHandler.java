package com.mercateo.exception;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

  @Override
  public void uncaughtException(final Thread thread, final Throwable error) {
    System.out.println("caught global exception " + thread + " - " + error);
  }
}
