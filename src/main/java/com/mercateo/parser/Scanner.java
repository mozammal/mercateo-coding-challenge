package com.mercateo.parser;

/**
 *
 *
 * <h1>Scanner</h1>
 *
 * <p>Subclasses need to implement this class
 */
public abstract class Scanner {

  protected FileReader fileReader;

  protected Token currentToken;

  public Scanner(FileReader fileReader) {
    this.fileReader = fileReader;
  }

  public FileReader getSource() {
    return fileReader;
  }

  /** return the current token */
  public Token getCurrentToken() {
    return currentToken;
  }

  /** return next token. */
  public Token nextToken() {
    currentToken = extractToken();
    return currentToken;
  }

  /** return next character from source file */
  public char nextChar() {
    return fileReader.nextChar();
  }

  /** return current character from source file */
  public char currentChar() {
    return fileReader.currentChar();
  }

  /** Needs to be implemented by subclasses */
  protected abstract Token extractToken();
}
