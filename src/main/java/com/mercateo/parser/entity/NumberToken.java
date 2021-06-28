package com.mercateo.parser.entity;

import com.mercateo.parser.FileReader;
import com.mercateo.parser.Token;

import static com.mercateo.parser.entity.EntityTokenType.*;

/**
 *
 *
 * <h1>NumberToken</h1>
 *
 * <p>number tokens (integer and real).
 */
public class NumberToken extends Token {

  public NumberToken(FileReader fileReader) {
    super(fileReader);
  }

  protected void extract() {
    StringBuilder textBuffer = new StringBuilder();
    extractNumber(textBuffer);
    text = textBuffer.toString();
  }

  /** Extract number token. */
  protected void extractNumber(StringBuilder textBuffer) {
    String wholeDigits = null; // digits before the decimal point
    String fractionDigits = null; // digits after the decimal point
    String exponentDigits = null; // exponent digits
    char exponentSign = '+'; // exponent sign '+' or '-'
    char currentChar; // current character

    type = INTEGER; // assume INTEGER token type

    // Extract whole part of the number
    wholeDigits = getUnsignedIntegerDigits(textBuffer);
    if (type == ERROR) {
      return;
    }

    // Is a .
    // It could be a decimal point
    currentChar = currentChar();
    if (currentChar == '.') {
      type = REAL_NUMBER;
      textBuffer.append(currentChar);
      currentChar = nextChar(); // consume decimal point

      // extract the fraction part of the number
      fractionDigits = getUnsignedIntegerDigits(textBuffer);
      if (type == ERROR) {
        return;
      }
    }

    // Is there an exponent part
    currentChar = currentChar();
    if (((currentChar == 'E') || (currentChar == 'e'))) {
      textBuffer.append(currentChar);
      currentChar = nextChar();
      type = REAL_NUMBER;

      // Exponent sign?
      //consume then
      if ((currentChar == '+') || (currentChar == '-')) {
        textBuffer.append(currentChar);
        exponentSign = currentChar;
        currentChar = nextChar();
      }
      // Extract whole part of exponent.
      exponentDigits = getUnsignedIntegerDigits(textBuffer);
    }
    if (type == INTEGER && !isInteger(textBuffer.toString())) {
      type = ERROR;
      return;
    }
  }

  /**
   * Extract and return the digits of an unsigned integer.
   */
  private String getUnsignedIntegerDigits(StringBuilder textBuffer) {
    char currentChar = currentChar();

    // Must have at least one digit
    if (!Character.isDigit(currentChar)) {
      type = ERROR;
      return null;
    }

    // Extract the digits
    StringBuilder digits = new StringBuilder();
    while (Character.isDigit(currentChar)) {
      textBuffer.append(currentChar);
      digits.append(currentChar);
      currentChar = nextChar();
    }
    return digits.toString();
  }

  /**
   * test if a string is an integer
   */
  private boolean isInteger(final String str) {
    try {
      Integer.valueOf(str.trim());
      return true;
    } catch (NumberFormatException nfe) {
      return false;
    }
  }
}
