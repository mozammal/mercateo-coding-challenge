package com.mercateo.utility;

import com.mercateo.model.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *
 * <h1>Utility</h1>
 *
 * <p>Utility class is for various method used across the application
 */
public class Utility {

  private Utility() {}

  /** this method add BigDecimal numbers */
  public static BigDecimal getSum(List<BigDecimal> numbers) {
    return numbers.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  /** this method return total weight from a list of items */
  public static BigDecimal getTotalWeight(List<Item> items) {
    return getSum(items.stream().map(item -> item.getWeight()).collect(Collectors.toList()));
  }

  /** this method return total cost from a list of items */
  public static BigDecimal getTotalCost(List<Item> items) {
    return getSum(
        items.stream().map(item -> item.getCost().getAmount()).collect(Collectors.toList()));
  }
}
