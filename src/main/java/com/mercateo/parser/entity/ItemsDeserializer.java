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

/**
 *
 *
 * <h1>ItemsDeserializer</h1>
 *
 * <p>Item deserializer for deserializing items from the file
 */
public class ItemsDeserializer extends Parser {

  private Token token;

  public ItemsDeserializer(Scanner scanner) {
    super(scanner);
  }

  /** Parse the source file and generate items */
  @Override
  public List<? extends Entity> parse() {
    List<List<String>> listOfItemProperties = new ArrayList<>();

    // loop over until encounter a new line token
    while (!((token = nextToken()) instanceof EofLineToken)) {
      List<String> itemProperty = new ArrayList<>();

      // is the current token a left paren?
      // if not then the current token is invalid
      // and raise an exception with line number and position of the token
      if (token.getType() != EntityTokenType.LEFT_PAREN) {
        throw new InvalidTokenException(formatParserExceptionMsg(token, itemTokenOrderList.get(0)));
      }

      // iterate over token sequences of item specified in the grammar rules in the READ.md,
      // excluding the left paren token
      for (String expectedToken : itemTokenOrderList.subList(1, itemTokenOrderList.size())) {
        token = nextToken(); // get next token

        // raise exception if current token is invalid
        raiseExceptionIfInvalidToken(token, expectedToken);

        if (token instanceof NumberToken) { // if the token is number, add it to the property list
          itemProperty.add(token.getText());
        } else if ((token instanceof SpecialSymbolToken)
            && EntityTokenType.EURO_USD
                .toString()
                .contains(
                    token
                        .getType()
                        .toString())) { // if the token is dollar sign or euro sign, add it to the
          // property list
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

  /** construct items from property list */
  private List<Item> mapToItems(List<List<String>> properties) {
    List<Item> items = new ArrayList<>();

    for (List<String> attr : properties) {
      Item itm = (Item) EntityFactory.create(attr, ITEM);
      items.add(itm);
    }
    return items;
  }

  /**
   * raise exception if current token doesnt much with the token required to validate the grammar
   * rules
   */
  private void raiseExceptionIfInvalidToken(Token token, String expectedToken) {
    String type = token.getType().toString();

    // Is the expected token for the id of item an Integer?
    // if not integer, the raise an exception
    // since Number token can be Integer or Real, so we need this special check here
    if (expectedToken.equals(INTEGER_TOKEN) && token.getType() != EntityTokenType.INTEGER) {
      throw new InvalidTokenException(formatParserExceptionMsg(token, expectedToken));
    } else if (!expectedToken.contains(type)) {
      throw new InvalidTokenException(formatParserExceptionMsg(token, expectedToken));
    }
  }
}
