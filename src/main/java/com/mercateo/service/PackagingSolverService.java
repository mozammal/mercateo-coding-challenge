package com.mercateo.service;

import com.mercateo.model.Entity;

import java.util.List;

/**
 *
 *
 * <h1>PackagingSolverService</h1>
 *
 * <p>Base interface responsible for finding the optimal solution
 */
public interface PackagingSolverService {

  List<?extends Entity> getResult();
}
