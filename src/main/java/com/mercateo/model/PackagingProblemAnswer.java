package com.mercateo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mercateo.utility.Utility.getSum;

public class PackagingProblemAnswer implements Entity {

  private BigDecimal cost = new BigDecimal(Double.MIN_VALUE);

  private List<Item> items;

  public PackagingProblemAnswer() {
    this.items = new ArrayList<>();
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public List<Item> getItems() {
    return items;
  }

  public BigDecimal getCost() {
    return cost;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }

  public BigDecimal getTotalWeight() {
    return getSum(items.stream().map(item -> item.getWeight()).collect(Collectors.toList()));
  }
}
