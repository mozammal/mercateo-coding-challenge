package com.mercateo.parser;

public abstract class Scanner {

  protected FileReader fileReader;

  protected Token currentToken;

  public Scanner(FileReader fileReader) {
    this.fileReader = fileReader;
  }

  public FileReader getSource() {
    return fileReader;
  }

  public Token getCurrentToken() {
    return currentToken;
  }

  public Token nextToken() {
    currentToken = extractToken();
    return currentToken;
  }

  public char nextChar() {
    return fileReader.nextChar();
  }

  public char currentChar() {
    return fileReader.currentChar();
  }

  protected abstract Token extractToken();
}
