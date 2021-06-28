package com.mercateo.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

public class Item implements Entity {


  private Integer id;

  private BigDecimal weight;

  private Money cost;

  public Item(Integer id, BigDecimal weight, Money cost) {
    this.weight = weight;
    this.cost = cost;
    this.id = id;
  }

  public Item() {}

  public BigDecimal getWeight() {
    return weight;
  }

  public void setWeight(BigDecimal weight) {
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Item item = (Item) o;
    return Objects.equals(id, item.id)
        && Objects.equals(weight, item.weight)
        && Objects.equals(cost, item.cost);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, weight, cost);
  }

  @Override
  public String toString() {
    return "Item{" + "id=" + id + ", weight=" + weight + ", cost=" + cost + '}';
  }
}
