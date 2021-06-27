package com.mercateo.exception;

import com.mercateo.parser.Token;

public class ExceptionMessageFormatter {

  private ExceptionMessageFormatter() {}

  public static String formatParserExceptionMsg(Token token, String... params) {

    StringBuilder msg = new StringBuilder();
    msg.append("Unknown token at: ").append("\n").append(token.getTokenDetails());
    if (params.length != 0 && params.length > 0) {
      msg.append("\n").append("Must be a ").append(params[0]);
    }
    return msg.toString();
  }
}
