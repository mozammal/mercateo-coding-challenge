package com.mercateo.parser.entity;

import com.mercateo.parser.FileReader;
import com.mercateo.parser.Token;

import static com.mercateo.parser.entity.EntityTokenType.REAL;

public class NumberToken extends Token {

  public NumberToken(FileReader fileReader) {
    super(fileReader);
  }

  protected void extract() {
    StringBuilder textBuffer = new StringBuilder();
    extractNumber(textBuffer);
    text = textBuffer.toString();
  }

  protected void extractNumber(StringBuilder textBuffer) {
    String wholeDigits = null;
    String fractionDigits = null;
    String exponentDigits = null;
    char exponentSign = '+';
    boolean sawDotDot = false;
    char currentChar;
    type = REAL;

    wholeDigits = unsignedIntegerDigits(textBuffer);

    currentChar = currentChar();
    if (currentChar == '.') {
      if (peekChar() == '.') {
        sawDotDot = true;
      } else {
        textBuffer.append(currentChar);
        currentChar = nextChar();
        fractionDigits = unsignedIntegerDigits(textBuffer);
      }
    }

    currentChar = currentChar();
    if (!sawDotDot && ((currentChar == 'E') || (currentChar == 'e'))) {
      textBuffer.append(currentChar);
      currentChar = nextChar();

      if ((currentChar == '+') || (currentChar == '-')) {
        textBuffer.append(currentChar);
        exponentSign = currentChar;
        currentChar = nextChar();
      }
      exponentDigits = unsignedIntegerDigits(textBuffer);
    }
  }

  private String unsignedIntegerDigits(StringBuilder textBuffer) {
    char currentChar = currentChar();

    if (!Character.isDigit(currentChar)) {
      return null;
    }

    StringBuilder digits = new StringBuilder();
    while (Character.isDigit(currentChar)) {
      textBuffer.append(currentChar);
      digits.append(currentChar);
      currentChar = nextChar();
    }
    return digits.toString();
  }
}
