package com.mercateo.parser.entity;

import com.mercateo.exception.EntitySyntaxViolation;
import com.mercateo.model.Entity;
import com.mercateo.model.Item;
import com.mercateo.model.EntityFactory;
import com.mercateo.model.Package;
import com.mercateo.parser.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityParser extends Parser {

  private List<String> parseRules;

  private Token token;

  public EntityParser(Scanner scanner) {
    super(scanner);
    parseRules =
        Arrays.asList(
            "REAL", "COMMA", "REAL", "COMMA", "EURO_SYMBOL/DOLLAR_SYMBOL", "REAL", "RIGHT_PAREN");
  }

  private Double getLeftEntity() {
    Double packageCapacity;
    if (!(token instanceof NumberToken)) {
      throw new EntitySyntaxViolation(
          "Unknown token at: " + token.getTokenDetails() + " Must be a Number");
    }
    packageCapacity = Double.valueOf(token.getText());
    token = nextToken();
    if (!(token.getType() == EntityTokenType.SEMI_COLON)) {
      throw new EntitySyntaxViolation(
          "Unknown token at: " + token.getTokenDetails() + "\nMust be a Semicolon");
    }
    return packageCapacity;
  }

  private List<List<String>> getRightEntity() {
    List<List<String>> attrs = new ArrayList<>();
    while (!((token = nextToken()) instanceof EofLineToken)) {
      if (token.getType() != EntityTokenType.LEFT_PAREN) {
        throw new EntitySyntaxViolation(
            "Unknown token at: " + token.getTokenDetails() + "\nMust be a Left parenthesis");
      }
      List<String> itemAttrs = new ArrayList<>();
      for (String rule : parseRules) {
        token = nextToken();
        String type = token.getType().toString();
        if (!rule.contains(type)) {
          throw new EntitySyntaxViolation(
              "Unknown token at: " + token.getTokenDetails() + " Must be a " + rule);
        }
        if (token instanceof NumberToken) {
          itemAttrs.add(token.getText());
        } else if ((token instanceof EntitySpecialSymbolToken)
            && parseRules.get(4).contains(type)) {
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
