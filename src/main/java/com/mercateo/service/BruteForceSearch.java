package com.mercateo.service;

import com.mercateo.model.Item;
import com.mercateo.model.Package;
import com.mercateo.model.PackagingProblemAnswer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.mercateo.config.Config.*;

public class BruteForceSearch implements Algorithm<Package, Item> {

  private PackagingProblemAnswer packagingProblemAnswer;

  public BruteForceSearch() {
    this.packagingProblemAnswer = new PackagingProblemAnswer();
  }

  @Override
  public List<Item> findSolution(Package aPackage) {
    dfs(aPackage, aPackage.getItems(), 0, new ArrayList<Item>());
    return packagingProblemAnswer.getItems();
  }

  private BigDecimal getTotalCost(List<Item> items) {
    return items.stream()
        .map(item -> item.getCost().getAmount())
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private void dfs(
      Package aPackage, List<Item> items, int currentIndex, ArrayList<Item> curSolution) {
    if (items.size() == currentIndex) {
      Double weight = curSolution.stream().mapToDouble(item -> item.getWeight()).sum();
      if (weight.compareTo(aPackage.getCapacity()) > ZERO) {
        return;
      }

      BigDecimal cost = getTotalCost(curSolution);
      if (cost.compareTo(packagingProblemAnswer.getCost()) > ZERO) {
        packagingProblemAnswer.setCost(cost);
        packagingProblemAnswer.setItems(new ArrayList<>(curSolution));
      } else if (cost.compareTo(packagingProblemAnswer.getCost()) == ZERO
          && weight.compareTo(packagingProblemAnswer.getTotalWeight()) < ZERO) {
        packagingProblemAnswer.setItems(new ArrayList<>(curSolution));
      }
      return;
    }
    curSolution.add(items.get(currentIndex));
    dfs(aPackage, items, currentIndex + ONE, curSolution);
    curSolution.remove(curSolution.size() - ONE);
    dfs(aPackage, items, currentIndex + ONE, curSolution);
  }
}
