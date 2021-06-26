package com.mercateo.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

public class EntityFactory {

  public static Entity create(List<String> attrs, String type) {

    if ("item".equalsIgnoreCase(type)) {
      Item item =
          new Item(
              Integer.valueOf(attrs.get(0)),
              Double.valueOf(attrs.get(1)),
              Money.money(
                  new BigDecimal(attrs.get(attrs.size() - 1)), Currency.getInstance("USD")));
      return item;
    } else if ("package".equalsIgnoreCase(type)) {
      return new Package(Double.valueOf(attrs.get(0)));
    } else return null;
  }
}
