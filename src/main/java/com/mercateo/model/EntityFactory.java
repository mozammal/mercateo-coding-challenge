package com.mercateo.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import static com.mercateo.config.Config.*;

public class EntityFactory {

  public static Entity create(List<String> properties, String type) {

    if (ITEM.equalsIgnoreCase(type)) {
      Item item =
          new Item(
              Integer.valueOf(properties.get(0)),
              Double.valueOf(properties.get(1)),
              Money.money(
                  new BigDecimal(properties.get(properties.size() - 1)),
                  properties.get(properties.size() - 2).equals(DOLLAR_SYMBOL)
                      ? Currency.getInstance(USD)
                      : Currency.getInstance(EUR)));
      return item;
    } else if (PACKAGE.equalsIgnoreCase(type)) {
      return new Package(Double.valueOf(properties.get(0)));
    }
    throw new UnsupportedOperationException();
  }
}
