package com.mercateo.service;

import com.mercateo.model.Item;
import com.mercateo.model.Package;
import com.mercateo.model.Result;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BruteForceSearch implements Algorithm<Package, Item> {

  private Result result;

  private Double MAX_VALUE = Double.MIN_VALUE;

  public BruteForceSearch() {
    this.result = new Result();
  }

  @Override
  public List<Item> findSolution(Package aPackage) {
    dfs(aPackage, aPackage.getItems(), 0, new ArrayList<Item>());
    return result.getItems();
  }

  private BigDecimal getCost(List<Item> items) {
    return items.stream()
        .map(item -> item.getCost().getAmount())
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private void dfs(
      Package aPackage, List<Item> items, int toBeSelected, ArrayList<Item> curSolution) {
    if (items.size() == toBeSelected) {
      Double weight = curSolution.stream().mapToDouble(item -> item.getWeight()).sum();
      if (weight.compareTo(aPackage.getCapacity()) > 0) {
        return;
      }
      BigDecimal cost = getCost(curSolution);
      if (cost.compareTo(result.getCost()) > 0) {
        result.setCost(cost);
        result.setItems(new ArrayList<>(curSolution));
      } else if (cost.compareTo(result.getCost()) == 0
          && weight.compareTo(result.getTotalWeight()) < 0) {
        result.setItems(new ArrayList<>(curSolution));
      }
      return;
    }
    curSolution.add(items.get(toBeSelected));
    dfs(aPackage, items, toBeSelected + 1, curSolution);
    curSolution.remove(curSolution.size() - 1);
    dfs(aPackage, items, toBeSelected + 1, curSolution);
  }
}
