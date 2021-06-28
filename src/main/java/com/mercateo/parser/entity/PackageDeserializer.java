package com.mercateo.parser.entity;

import com.mercateo.exception.InvalidTokenException;
import com.mercateo.model.Entity;
import com.mercateo.model.EntityFactory;
import com.mercateo.model.Package;
import com.mercateo.parser.Parser;
import com.mercateo.parser.Scanner;
import com.mercateo.parser.Token;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.mercateo.config.Config.*;
import static com.mercateo.exception.ExceptionMessageFormatter.formatParserExceptionMsg;

/**
 *
 *
 * <h1>PackageDeserializer</h1>
 *
 * <p>Package deserializer for deserializing package from the file
 */
public class PackageDeserializer extends Parser {

  private Token token;

  public PackageDeserializer(Scanner scanner) {
    super(scanner);
  }

  /** Parse the source file and generate package */
  @Override
  public List<? extends Entity> parse() {
    BigDecimal packageCapacity;

    // Is the first token a number?
    // if not a number, then raise exception
    if (!(token instanceof NumberToken)) {
      throw new InvalidTokenException(
          formatParserExceptionMsg(token, packageTokenOrderList.get(0))); // show error message
    }
    packageCapacity = new BigDecimal(token.getText());

    token = nextToken();

    // Is the last token a semi colon?
    // raise exception if not a semicolon
    if (!(token.getType() == EntityTokenType.SEMI_COLON)) {
      throw new InvalidTokenException(
          formatParserExceptionMsg(token, packageTokenOrderList.get(1)));
    }
    // construct package
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
