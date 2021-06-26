package com.mercateo.model;

public class Package {

  private Double capacity;

  public Package(Double capacity) {
    this.capacity = capacity;
  }

  public Double getCapacity() {
    return capacity;
  }

  public void setCapacity(Double capacity) {
    this.capacity = capacity;
  }

  @Override
  public String toString() {
    return "Package{" + "capacity=" + capacity + '}';
  }
}
