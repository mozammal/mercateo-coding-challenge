package com.mercateo.parser;

import com.mercateo.model.Entity;

import java.util.List;

/**
 *
 *
 * <h1>Parser</h1>
 *
 * <p>Base parser class
 */
public abstract class Parser {

  private Scanner scanner;

  public Parser(Scanner scanner) {
    this.scanner = scanner;
  }

  public Scanner getScanner() {
    return scanner;
  }

  public Token nextToken() {
    return scanner.nextToken();
  }

  public Token currentToken() {
    return scanner.getCurrentToken();
  }

  /** parse a source program. Subclasses need to implement this */
  public abstract List<? extends Entity> parse();
}
