package com.mercateo.model;

public class Item {

  private Double weight;

  private Money cost;

  public Item(Double weight, Money cost) {
    this.weight = weight;
    this.cost = cost;
  }

  public Double getWeight() {
    return weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }

  public Money getCost() {
    return cost;
  }

  public void setCost(Money cost) {
    this.cost = cost;
  }

  @Override
  public String toString() {
    return "Item{" + "weight=" + weight + ", cost=" + cost + '}';
  }
}
