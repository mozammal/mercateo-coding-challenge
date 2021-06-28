package com.mercateo.parser.entity;

import com.mercateo.parser.FileReader;

import static com.mercateo.parser.entity.EntityTokenType.ERROR;

/**
 *
 *
 * <h1>SpecialSymbolToken</h1>
 *
 * <p>special symbol tokens.
 */
public class SpecialSymbolToken extends EntityToken {

  public SpecialSymbolToken(FileReader fileReader) {
    super(fileReader);
  }

  /** Extract special symbol token */
  protected void extract() {
    char currentChar = currentChar();
    text = Character.toString(currentChar);
    type = null;

    switch (text) {
      case "(":
      case ")":
      case ",":
      case "€":
      case "$":
      case ":":
        nextChar();
        break;

      default:
        type = ERROR;
        nextChar();
    }
    if (type == null) {
      type = EntityTokenType.SPECIAL_SYMBOLS.get(text);
    }
  }
}
