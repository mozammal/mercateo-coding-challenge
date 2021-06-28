package com.mercateo.service;

import com.mercateo.algorithm.Algorithm;
import com.mercateo.model.Entity;
import com.mercateo.model.Package;

import java.util.List;

/**
 *
 *
 * <h1>BruteForcePackagingSolverService</h1>
 *
 * <p>This class is responsible for finding the optimal solution
 */
public class BruteForcePackagingSolverService implements PackagingSolverService {

  private final Package aPackage;

  private final Algorithm algorithm;

  public BruteForcePackagingSolverService(Package aPackage, Algorithm algorithm) {
    this.aPackage = aPackage;
    this.algorithm = algorithm;
  }

  /**
   * return optimal solution list using the specified algorithm injected in the constructor of this
   * class
   */
  @Override
  public List<? extends Entity> getResult() {
    return algorithm.findSolution(aPackage);
  }
}
