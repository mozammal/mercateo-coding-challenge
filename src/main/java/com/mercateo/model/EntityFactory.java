package com.mercateo.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import static com.mercateo.config.Config.*;

/**
 *
 *
 * <h1>EntityFactory</h1>
 *
 * <p>Factory that creates items and packages
 */
public class EntityFactory {

  /** create items and packages from the properties */
  public static Entity create(List<String> properties, String type) {

    if (ITEM.equalsIgnoreCase(type)) {
      Item item =
          new Item(
              Integer.valueOf(properties.get(0)),
              new BigDecimal(properties.get(1)),
              Money.money(
                  new BigDecimal(properties.get(properties.size() - 1)),
                  properties.get(properties.size() - 2).equals(DOLLAR_SYMBOL)
                      ? Currency.getInstance(USD)
                      : Currency.getInstance(EUR)));
      return item;
    } else if (PACKAGE.equalsIgnoreCase(type)) {
      return new Package(new BigDecimal(properties.get(0)));
    }
    throw new UnsupportedOperationException();
  }
}
