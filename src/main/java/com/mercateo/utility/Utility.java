package com.mercateo.utility;

import com.mercateo.model.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Utility {

  private Utility() {}

  public static BigDecimal getSum(List<BigDecimal> numbers) {
    return numbers.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public static BigDecimal getTotalWeight(List<Item> items) {
    return getSum(items.stream().map(item -> item.getWeight()).collect(Collectors.toList()));
  }

  public static BigDecimal getTotalCost(List<Item> items) {
    return getSum(
        items.stream().map(item -> item.getCost().getAmount()).collect(Collectors.toList()));
  }
}
