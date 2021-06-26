package com.mercateo.parser;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {

  public static final char EOL = '\n';

  public static final char EOF = (char) 0;

  private BufferedReader reader;

  private String line;

  private int currentPos;

  private int lineNumber;

  public FileReader(BufferedReader reader) {
    this.reader = reader;
    this.currentPos = -2;
    this.lineNumber = 0;
  }

  public int getCurrentPos() {
    return currentPos;
  }

  public char peekChar() {
    currentChar();
    if (line == null) {
      return EOF;
    }
    int nextPos = currentPos + 1;
    return nextPos < line.length() ? line.charAt(nextPos) : EOL;
  }

  public char currentChar() {
    if (currentPos == -2) {
      readLine();
      return nextChar();
    } else if (line == null) {
      return EOF;
    } else if ((currentPos == -1) || (currentPos == line.length())) {
      return EOL;
    } else if (currentPos > line.length()) {
      readLine();
      return nextChar();
    } else {
      return line.charAt(currentPos);
    }
  }

  private void readLine() {
    try {
      line = reader.readLine();
      currentPos = -1;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public char nextChar() {
    ++currentPos;
    return currentChar();
  }

  public int getLineNumber() {
    return lineNumber;
  }
}
