package com.mercateo.parser.entity;

import com.mercateo.exception.InvalidTokenException;
import com.mercateo.model.Entity;
import com.mercateo.model.EntityFactory;
import com.mercateo.model.Item;
import com.mercateo.parser.EofLineToken;
import com.mercateo.parser.Parser;
import com.mercateo.parser.Scanner;
import com.mercateo.parser.Token;

import java.util.ArrayList;
import java.util.List;

import static com.mercateo.config.Config.*;
import static com.mercateo.exception.ExceptionMessageFormatter.formatParserExceptionMsg;

public class ItemsDeserializer extends Parser {

  private Token token;

  public ItemsDeserializer(Scanner scanner) {
    super(scanner);
  }

  @Override
  public List<? extends Entity> parse() {
    List<List<String>> listOfItemProperties = new ArrayList<>();

    while (!((token = nextToken()) instanceof EofLineToken)) {
      List<String> itemProperty = new ArrayList<>();
      if (token.getType() != EntityTokenType.LEFT_PAREN) {
        throw new InvalidTokenException(formatParserExceptionMsg(token, itemTokenOrderList.get(0)));
      }

      for (String expectedToken : itemTokenOrderList.subList(1, itemTokenOrderList.size())) {
        token = nextToken();
        raiseExceptionIfInvalidToken(token, expectedToken);
        if (token instanceof NumberToken) {
          itemProperty.add(token.getText());
        } else if ((token instanceof EntitySpecialSymbolToken)
            && EntityTokenType.EURO_USD.toString().contains(token.getType().toString())) {
          itemProperty.add(token.getText());
        }
      }
      listOfItemProperties.add(new ArrayList<>(itemProperty));
    }
    return mapToItems(listOfItemProperties);
  }

  public void setToken(Token token) {
    this.token = token;
  }

  public Token getToken() {
    return token;
  }

  private List<Item> mapToItems(List<List<String>> properties) {
    List<Item> items = new ArrayList<>();

    for (List<String> attr : properties) {
      Item itm = (Item) EntityFactory.create(attr, ITEM);
      items.add(itm);
    }
    return items;
  }

  private void raiseExceptionIfInvalidToken(Token token, String expectedToken) {
    String type = token.getType().toString();

    if (expectedToken.equals(INTEGER_TOKEN) && token.getType() != EntityTokenType.INTEGER) {
      throw new InvalidTokenException(formatParserExceptionMsg(token, expectedToken));
    } else if (!expectedToken.contains(type)) {
      throw new InvalidTokenException(formatParserExceptionMsg(token, expectedToken));
    }
  }
}
