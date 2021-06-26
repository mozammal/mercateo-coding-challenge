package com.mercateo.model;

import java.util.ArrayList;
import java.util.List;

public class Result implements Entity {

  private List<Item> items;

  public Result() {
    this.items = new ArrayList<>();
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public List<Item> getItems() {
    return items;
  }

  public Double getTotalWeight() {
    return items.stream().mapToDouble(item -> item.getWeight()).sum();
  }
}
