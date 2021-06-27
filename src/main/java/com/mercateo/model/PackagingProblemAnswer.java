package com.mercateo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

  public Double getTotalWeight() {
    return items.stream().mapToDouble(item -> item.getWeight()).sum();
  }
}
