package com.mercateo.service;

import com.mercateo.model.Entity;
import com.mercateo.model.Package;

import java.util.List;

public class BruteForcePackagingSolverService implements PackagingSolverService {

  private final Package aPackage;

  private final Algorithm algorithm;

  public BruteForcePackagingSolverService(Package aPackage, Algorithm algorithm) {
    this.aPackage = aPackage;
    this.algorithm = algorithm;
  }

  @Override
  public List<? extends Entity> getResult() {
    return algorithm.findSolution(aPackage);
  }
}
