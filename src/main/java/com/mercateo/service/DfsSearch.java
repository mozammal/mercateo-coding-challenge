package com.mercateo.service;

import com.mercateo.model.Item;
import com.mercateo.model.Result;

import java.util.ArrayList;
import java.util.List;

public class DfsSearch implements Algorithm<Package, Item> {

  private Result result;

  private Double MAX_VALUE = Double.MIN_VALUE;

  public DfsSearch() {
    this.result = new Result();
  }

  @Override
  public List<Item> execute(Package aPackage, List<Item> items) {
    dfs(aPackage, items, 0, new ArrayList<Item>());
    return result.getItems();
  }

  private void dfs(Package aPackage, List<Item> items, int depth, ArrayList<Item> cur) {
    /*    if (items.size() == depth) {
      Double weight = cur.stream().mapToDouble(item -> item.getWeight()).sum();
      Double cost = cur.stream().mapToDouble(item -> item.getCost().getAmount()).sum();
      if (cost.compareTo(MAX_VALUE) > 0) {
        MAX_VALUE = cost;
        result.setItems(new ArrayList<>(cur));
      } else if (cost.compareTo(MAX_VALUE) == 0 && weight.compareTo(result.getTotalWeight()) < 0) {
        result.setItems(new ArrayList<>(cur));
      }
    }
    cur.add(items.get(depth));
    dfs(aPackage, items, depth + 1, cur);
    cur.remove(cur.size() - 1);
    dfs(aPackage, items, depth + 1, cur);*/
  }
}
