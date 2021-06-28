package com.mercateo.parser;

import java.io.BufferedReader;
import java.io.IOException;

import static com.mercateo.config.Config.*;

/**
 *
 *
 * <h1>FileReader</h1>
 *
 * <p>This class represents the source file
 */
public class FileReader {

  private BufferedReader reader;

  private String line;

  private int currentPos;

  private int lineNumber;

  public FileReader(BufferedReader reader) {
    this.reader = reader;
    this.currentPos = -2;
    this.lineNumber = 0;
  }

  /** get current source position. */
  public int getCurrentPos() {
    return currentPos;
  }

  /** return current character without consuming it */
  public char peekChar() {
    currentChar();
    if (line == null) {
      return EOF;
    }
    int nextPos = currentPos + 1;
    return nextPos < line.length() ? line.charAt(nextPos) : EOL;
  }

  /** return the character at the current position */
  public char currentChar() {

    // first time reading
    if (currentPos == -2) {
      readLine();
      return nextChar();
    } else if (line == null) { // EOF?
      return EOF;
    } else if ((currentPos == -1) || (currentPos == line.length())) { // end of line
      return EOL;
    } else if (currentPos > line.length()) { // read next line
      readLine();
      return nextChar();
    } else { // return cur character
      return line.charAt(currentPos);
    }
  }

  /** read the next line */
  private void readLine() {
    try {
      line = reader.readLine();
      currentPos = -1;

      if (line != null) {
        lineNumber++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  /** consume the current source character and return the next character */
  public char nextChar() {
    ++currentPos;
    return currentChar();
  }

  public int getLineNumber() {
    return lineNumber;
  }
}
