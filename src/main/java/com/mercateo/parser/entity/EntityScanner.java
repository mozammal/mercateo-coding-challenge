package com.mercateo.parser.entity;

import com.mercateo.parser.*;

import static com.mercateo.parser.FileReader.EOF;
import static com.mercateo.parser.FileReader.EOL;

public class EntityScanner extends Scanner {

  public EntityScanner(FileReader fileReader) {
    super(fileReader);
  }

  @Override
  protected Token extractToken() {
    char currentChar = currentChar();
    if (currentChar != EOL) skipWhiteSpace();

    Token token;

    currentChar = currentChar();
    if (currentChar == EOF) {
      token = new EofToken(fileReader);
    } else if (currentChar == EOL) {
      token = new EofLineToken(fileReader);
    } else if (Character.isDigit(currentChar)) {
      token = new NumberToken(fileReader);
    } else if (EntityTokenType.SPECIAL_SYMBOLS.containsKey(Character.toString(currentChar))) {
      token = new EntitySpecialSymbolToken(fileReader);
    } else {
      token = null;
    }
    return token;
  }

  private void skipWhiteSpace() {
    char currentChar = currentChar();

    while (Character.isWhitespace(currentChar)) {
      currentChar = nextChar();
    }
  }
}
