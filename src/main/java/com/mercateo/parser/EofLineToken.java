package com.mercateo.parser;

public class EofLineToken extends Token {
  public EofLineToken(FileReader fileReader) {
    super(fileReader);
  }

  protected void extract() {
    nextChar();
  }
}
