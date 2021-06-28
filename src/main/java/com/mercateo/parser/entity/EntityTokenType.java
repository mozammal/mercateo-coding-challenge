package com.mercateo.parser.entity;

import com.mercateo.parser.TokenType;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * <h1>EntityTokenType</h1>
 *
 * <p>Entity token types
 */
public enum EntityTokenType implements TokenType {

  // Special symbols
  SEMI_COLON(":"),
  LEFT_PAREN("("),
  COMMA(","),
  EURO_SYMBOL("€"),
  DOLLAR_SYMBOL("$"),
  RIGHT_PAREN(")"),
  INTEGER,
  REAL_NUMBER,
  EURO_USD("EURO_SYMBOL/DOLLAR_SYMBOL"),
  ERROR;

  private String text;

  EntityTokenType(String text) {
    this.text = text;
  }

  EntityTokenType() {
    this.text = this.toString().toLowerCase();
  }

  public String getText() {
    return text;
  }

  public static Map<String, EntityTokenType> SPECIAL_SYMBOLS = new HashMap<>();

  static {
    EntityTokenType values[] = EntityTokenType.values();
    for (int i = SEMI_COLON.ordinal(); i <= RIGHT_PAREN.ordinal(); ++i) {
      SPECIAL_SYMBOLS.put(values[i].getText(), values[i]);
    }
  }
}
