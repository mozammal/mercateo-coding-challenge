package com.mercateo.parser;

import com.mercateo.model.Entity;

import java.util.List;

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

  public abstract List<? extends Entity> parse();
}
