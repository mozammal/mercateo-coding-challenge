package com.mercateo.service;

import com.mercateo.model.Entity;
import com.mercateo.model.Item;

import java.util.List;

public interface PackageSolver {

  List<?extends Entity> getResult();
}
