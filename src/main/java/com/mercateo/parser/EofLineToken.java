package com.mercateo.parser;

/**
 *
 *
 * <h1>EofLineToken</h1>
 *
 * <p>Eof line token to detect end of a line.
 */
public class EofLineToken extends Token {
  public EofLineToken(FileReader fileReader) {
    super(fileReader);
  }

  /** consume current character */
  protected void extract() {
    nextChar();
  }
}
