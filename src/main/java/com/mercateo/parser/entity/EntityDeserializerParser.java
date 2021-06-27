package com.mercateo.parser.entity;

import com.mercateo.exception.InvalidTokenException;
import com.mercateo.model.Entity;
import com.mercateo.model.Item;
import com.mercateo.model.EntityFactory;
import com.mercateo.model.Package;
import com.mercateo.parser.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mercateo.exception.ExceptionMessageFormatter.formatParserExceptionMsg;

public class EntityDeserializerParser extends Parser {

  private List<String> tokenOrderList;

  private Token token;

  public EntityDeserializerParser(Scanner scanner) {
    super(scanner);
    tokenOrderList =
        Arrays.asList(
            "INTEGER/REAL_NUMBER",
            "SEMI_COLON",
            "LEFT_PAREN",
            "INTEGER",
            "COMMA",
            "INTEGER/REAL_NUMBER",
            "COMMA",
            "EURO_SYMBOL/DOLLAR_SYMBOL",
            "INTEGER/REAL_NUMBER",
            "RIGHT_PAREN");
  }

  private Double getLeftEntity() {
    Double packageCapacity;
    if (!(token instanceof NumberToken)) {
      throw new InvalidTokenException(formatParserExceptionMsg(token, tokenOrderList.get(0)));
    }
    packageCapacity = Double.valueOf(token.getText());
    token = nextToken();
    if (!(token.getType() == EntityTokenType.SEMI_COLON)) {

      throw new InvalidTokenException(formatParserExceptionMsg(token, tokenOrderList.get(1)));
    }
    return packageCapacity;
  }

  private void raiseExceptionIfInvalidToken(Token token, String expectedToken) {
    String type = token.getType().toString();
    if (expectedToken.equals("INTEGER") && token.getType() != EntityTokenType.INTEGER) {
      throw new InvalidTokenException(formatParserExceptionMsg(token, expectedToken));
    } else if (!expectedToken.contains(type)) {
      throw new InvalidTokenException(formatParserExceptionMsg(token, expectedToken));
    }
  }

  private List<List<String>> getRightEntity() {
    List<List<String>> attrs = new ArrayList<>();
    while (!((token = nextToken()) instanceof EofLineToken)) {
      if (token.getType() != EntityTokenType.LEFT_PAREN) {
        throw new InvalidTokenException(formatParserExceptionMsg(token, tokenOrderList.get(2)));
      }
      List<String> itemAttrs = new ArrayList<>();

      for (String expectedToken : tokenOrderList.subList(3, tokenOrderList.size())) {
        token = nextToken();
        raiseExceptionIfInvalidToken(token, expectedToken);
        if (token instanceof NumberToken) {
          itemAttrs.add(token.getText());
        } else if ((token instanceof EntitySpecialSymbolToken)
            && tokenOrderList.get(7).contains( token.getType().toString())) {
          itemAttrs.add(token.getText());
        }
      }
      attrs.add(new ArrayList<>(itemAttrs));
    }
    return attrs;
  }

  public List<? extends Entity> parse() {
    List<Entity> packages = new ArrayList<>();

    while (!((token = nextToken()) instanceof EofToken)) {
      if (token instanceof EofLineToken) {
        continue;
      }

      List<Item> items = new ArrayList<>();
      Double packageCapacity = getLeftEntity();
      List<List<String>> attrs = getRightEntity();
      addItemsToPackage(packages, items, packageCapacity, attrs);
    }
    return packages;
  }

  private void addItemsToPackage(
      List<Entity> packages, List<Item> items, Double packageCapacity, List<List<String>> attrs) {
    Package aPackage =
        (Package) EntityFactory.create(Arrays.asList(String.valueOf(packageCapacity)), "package");
    for (List<String> attr : attrs) {
      Item itm = (Item) EntityFactory.create(attr, "item");
      items.add(itm);
    }
    aPackage.setItems(items);
    packages.add(aPackage);
  }
}
