package com.mercateo.parser.entity;

import com.mercateo.parser.FileReader;

import static com.mercateo.parser.entity.EntityTokenType.ERROR;

public class EntitySpecialSymbolToken extends EntityToken {

  public EntitySpecialSymbolToken(FileReader fileReader) {
    super(fileReader);
  }

  protected void extract() {
    char currentChar = currentChar();
    text = Character.toString(currentChar);
    type = null;

    switch (currentChar) {
      case '(':
      case ')':
      case ',':
      case '€':
      case '$':
      case ':':
        nextChar();
        break;

      default:
        type = ERROR;
        nextChar(); // consume bad character
    }
    if (type == null) {
      type = EntityTokenType.SPECIAL_SYMBOLS.get(text);
    }
  }
}
