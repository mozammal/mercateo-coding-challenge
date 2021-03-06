package com.mercateo.config;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * <h1>Config</h1>
 *
 * <p>This utility class represents configuration used throught the application
 */
public class Config {

  public static final String ITEM = "item";

  public static final String PACKAGE = "package";

  public static final String USD = "USD";

  public static final String EUR = "EUR";

  public static final String DOLLAR_SYMBOL = "$";

  public static final String INTEGER_TOKEN = "INTEGER";

  public static final String ENTITY_DESERIALIZER = "entity-deserializer";

  public static final String UTF = "UTF-8";

  public static final char EOL = '\n';

  public static final char EOF = (char) 0;

  public static final int ZERO = 0;

  public static final int ONE = 1;

  public static final int NEGATIVE = 0;

  public static final List<String> itemTokenOrderList =
      Arrays.asList(
          "LEFT_PAREN",
          "INTEGER",
          "COMMA",
          "INTEGER/REAL_NUMBER",
          "COMMA",
          "EURO_SYMBOL/DOLLAR_SYMBOL",
          "INTEGER/REAL_NUMBER",
          "RIGHT_PAREN");

  public static final List<String> packageTokenOrderList =
      Arrays.asList("INTEGER/REAL_NUMBER", "SEMI_COLON");

  public static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_UP;

  public static MathContext context = new MathContext(128, RoundingMode.HALF_UP);

  private Config() {}
}
