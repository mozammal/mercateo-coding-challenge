package com.mercateo.model;

import java.util.ArrayList;
import java.util.List;

public class Package implements Entity {

  private Double capacity;

  private List<Item> items;

  public Package(Double capacity, List<Item> items) {
    this.capacity = capacity;
    this.items = items;
  }

  public Package(Double capacity) {
    this(capacity, new ArrayList<>());
  }

  public Double getCapacity() {
    return capacity;
  }

  public void setCapacity(Double capacity) {
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
