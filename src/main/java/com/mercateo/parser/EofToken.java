package com.mercateo.parser;

public class EofToken extends Token {

  public EofToken(FileReader fileReader) {
    super(fileReader);
  }

  /** Do nothing */
  @Override
  protected void extract() {}
}
