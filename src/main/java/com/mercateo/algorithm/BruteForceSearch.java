package com.mercateo.algorithm;

import com.mercateo.model.Item;
import com.mercateo.model.Package;
import com.mercateo.model.PackagingProblemSolution;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.mercateo.config.Config.*;
import static com.mercateo.utility.Utility.*;

/**
 *
 *
 * <h1>BruteForceSearch</h1>
 *
 * <p>BruteForceSearch is an implementation of a brute-force algorithm
 */
public class BruteForceSearch implements Algorithm<Package, Item> {

  private PackagingProblemSolution packagingProblemSolution;

  public BruteForceSearch() {
    this.packagingProblemSolution = new PackagingProblemSolution();
  }

  /** find the optimal solution */
  @Override
  public List<Item> findSolution(Package aPackage) {
    exhaustiveSearch(aPackage, aPackage.getItems(), 0, new ArrayList<Item>());
    return packagingProblemSolution.getItems();
  }

  /**
   * An exhaustive search that finds the optimal solution. A brute-force way to do that is to
   * compute all subsets U that do not include a particular element. Then compute all subsets V
   * which do include that element. Each subset set must appear in U or in V, so the final result is
   * {u, V}. The algorithm is recursive, and the base case is when the input set is empty.
   *
   * <p>The time complexity is O(n*2^n), where n is the total number of elements.The space
   * complexity is O(n) due to recursion.
   */
  private void exhaustiveSearch(
      Package aPackage, List<Item> items, int currentIndex, ArrayList<Item> curSolution) {

    // Finish iterating the last item?
    // Yes, then may be a new solution?
    if (items.size() == currentIndex) {

      // total weight of all items in the current solution set
      BigDecimal totalWeight = getTotalWeight(curSolution);

      // return if total weight is greater than the package capacity
      if (totalWeight.compareTo(aPackage.getCapacity()) > ZERO) {
        return;
      }

      // total cost  of all items in the current solution set
      BigDecimal totalCost = getTotalCost(curSolution);

      // is the total cost greater than the solution found previously?
      if (totalCost.compareTo(packagingProblemSolution.getCost()) > ZERO) {
        packagingProblemSolution.setCost(totalCost); // store best cost so far
        packagingProblemSolution.setItems(
            new ArrayList<>(curSolution)); // store the best item set so far
      }
      // is the total cost equal to the solution found previously but total weight is less?
      else if (totalCost.compareTo(packagingProblemSolution.getCost()) == ZERO
          && totalWeight.compareTo(packagingProblemSolution.getTotalWeight()) < ZERO) {
        packagingProblemSolution.setItems(
            new ArrayList<>(curSolution)); // store the best item set so far
      }
      return;
    }

    // take the current item and recurse for the next item
    curSolution.add(items.get(currentIndex));
    exhaustiveSearch(aPackage, items, currentIndex + ONE, curSolution);

    // dont take the current item and recurse for the next item
    curSolution.remove(curSolution.size() - ONE);
    exhaustiveSearch(aPackage, items, currentIndex + ONE, curSolution);
  }
}
