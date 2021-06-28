package com.mercateo.exception;

import com.mercateo.parser.Token;

/**
 *
 *
 * <h1>ExceptionMessageFormatter</h1>
 *
 * <p>Message formatter for exception thrown by the parser
 */
public class ExceptionMessageFormatter {

  private ExceptionMessageFormatter() {}

  /** this method returns exception in a readable way so that uses can see what went wrong. */
  public static String formatParserExceptionMsg(Token token, String... params) {

    StringBuilder msg = new StringBuilder();
    msg.append("Unknown token at: ").append("\n").append(token.getTokenDetails());
    if (params.length != 0 && params.length > 0) {
      msg.append("\n").append("Must be a ").append(params[0]);
    }
    return msg.toString();
  }
}
