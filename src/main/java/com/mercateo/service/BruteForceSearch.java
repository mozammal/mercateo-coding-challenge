package com.mercateo.service;

import com.mercateo.model.Item;
import com.mercateo.model.Package;
import com.mercateo.model.PackagingProblemAnswer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.mercateo.config.Config.*;
import static com.mercateo.utility.Utility.*;

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

  private void dfs(
      Package aPackage, List<Item> items, int currentIndex, ArrayList<Item> curSolution) {
    if (items.size() == currentIndex) {
      BigDecimal totalWeight = getTotalWeight(curSolution);
      if (totalWeight.compareTo(aPackage.getCapacity()) > ZERO) {
        return;
      }

      BigDecimal totalCost = getTotalCost(curSolution);
      if (totalCost.compareTo(packagingProblemAnswer.getCost()) > ZERO) {
        packagingProblemAnswer.setCost(totalCost);
        packagingProblemAnswer.setItems(new ArrayList<>(curSolution));
      } else if (totalCost.compareTo(packagingProblemAnswer.getCost()) == ZERO
          && totalWeight.compareTo(packagingProblemAnswer.getTotalWeight()) < ZERO) {
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
