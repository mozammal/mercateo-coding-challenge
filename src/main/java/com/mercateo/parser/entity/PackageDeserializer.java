package com.mercateo.parser.entity;

import com.mercateo.exception.InvalidTokenException;
import com.mercateo.model.Entity;
import com.mercateo.model.EntityFactory;
import com.mercateo.model.Package;
import com.mercateo.parser.Parser;
import com.mercateo.parser.Scanner;
import com.mercateo.parser.Token;

import java.util.Arrays;
import java.util.List;

import static com.mercateo.config.Config.*;
import static com.mercateo.exception.ExceptionMessageFormatter.formatParserExceptionMsg;

public class PackageDeserializer extends Parser {

  private Token token;

  public PackageDeserializer(Scanner scanner) {
    super(scanner);
  }

  @Override
  public List<? extends Entity> parse() {
    Double packageCapacity;
    if (!(token instanceof NumberToken)) {
      throw new InvalidTokenException(
          formatParserExceptionMsg(token, packageTokenOrderList.get(0)));
    }
    packageCapacity = Double.valueOf(token.getText());
    token = nextToken();
    if (!(token.getType() == EntityTokenType.SEMI_COLON)) {

      throw new InvalidTokenException(
          formatParserExceptionMsg(token, packageTokenOrderList.get(1)));
    }
    Package aPackage =
        (Package) EntityFactory.create(Arrays.asList(String.valueOf(packageCapacity)), PACKAGE);
    return Arrays.asList(aPackage);
  }

  public void setToken(Token token) {
    this.token = token;
  }

  public Token getToken() {
    return token;
  }
}
