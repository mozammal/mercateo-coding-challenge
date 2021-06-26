package com.mercateo.model;

public class Item implements Entity {

  private Integer id;

  private Double weight;

  private Money cost;

  public Item(Integer id, Double weight, Money cost) {
    this.weight = weight;
    this.cost = cost;
    this.id = id;
  }

  public Item() {}

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

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Item{" + "id=" + id + ", weight=" + weight + ", cost=" + cost + '}';
  }
}
