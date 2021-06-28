package com.mercateo.parser;

/**
 *
 *
 * <h1>Token</h1>
 *
 * <p>Token class that uses Scanner to extract token
 */
public class Token {

  protected TokenType type;

  protected String text;

  protected int position;

  protected int lineNumber;

  protected FileReader fileReader;

  public Token(FileReader fileReader) {
    this.fileReader = fileReader;
    this.position = fileReader.getCurrentPos();
    this.lineNumber = fileReader.getLineNumber();
    extract();
  }

  /**
   * Default method to extract only one-character tokens After extracting the token, the current
   * source line position is moved by one position
   */
  protected void extract() {
    text = Character.toString(currentChar());
    nextChar();
  }

  /** token details used for error messages */
  public String getTokenDetails() {
    StringBuilder message = new StringBuilder();
    message
        .append("Line number: ")
        .append(lineNumber)
        .append("\n")
        .append("Position number: ")
        .append(position);
    return message.toString();
  }

  public String getText() {
    return text;
  }

  protected char nextChar() {
    return fileReader.nextChar();
  }

  protected char currentChar() {
    return fileReader.currentChar();
  }

  protected char peekChar() {
    return fileReader.peekChar();
  }

  public TokenType getType() {
    return type;
  }
}
