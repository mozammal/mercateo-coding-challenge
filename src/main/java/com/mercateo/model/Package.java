package com.mercateo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Package implements Entity {

  private BigDecimal capacity;

  private List<Item> items;

  public Package(BigDecimal capacity, List<Item> items) {
    this.capacity = capacity;
    this.items = items;
  }

  public Package(BigDecimal capacity) {
    this(capacity, new ArrayList<>());
  }

  public BigDecimal getCapacity() {
    return capacity;
  }

  public void setCapacity(BigDecimal capacity) {
    this.capacity = capacity;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  @Override
  public String toString() {
    return "Package{" + "capacity=" + capacity + ", items=" + items + '}';
  }
}
