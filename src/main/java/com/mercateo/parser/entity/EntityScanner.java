package com.mercateo.parser.entity;

import com.mercateo.parser.*;

import static com.mercateo.config.Config.EOF;
import static com.mercateo.config.Config.EOL;

/**
 *
 *
 * <h1>EntityScanner</h1>
 *
 * <p>Entity scanner class
 */
public class EntityScanner extends Scanner {

  public EntityScanner(FileReader fileReader) {
    super(fileReader);
  }

  /** extract the nextb token from file */
  @Override
  protected Token extractToken() {
    Token token;

    // current character
    char currentChar = currentChar();

    // is EOL?
    // skip white space if not end of line
    if (currentChar != EOL) skipWhiteSpace();

    // current character after skipping whitespace
    currentChar = currentChar();

    if (currentChar == EOF) { // EOF token
      token = new EofToken(fileReader);
    } else if (currentChar == EOL) { // EOL token
      token = new EofLineToken(fileReader);
    } else if (Character.isDigit(currentChar)) { // Number Token
      token = new NumberToken(fileReader);
    } else if (EntityTokenType.SPECIAL_SYMBOLS.containsKey(
        Character.toString(currentChar))) { // Special Token
      token = new SpecialSymbolToken(fileReader);
    } else { // Error Token
      token = new EntityErrorToken(fileReader);
    }
    return token;
  }

  /** skip whitespace */
  private void skipWhiteSpace() {
    char currentChar = currentChar();

    while (Character.isWhitespace(currentChar)) {
      currentChar = nextChar();
    }
  }
}
