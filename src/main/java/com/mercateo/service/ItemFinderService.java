package com.mercateo.service;

import com.mercateo.model.Item;

import java.util.List;

public class ItemFinderService implements FinderService {

  private final List<Item> items;

  private final Package aPackage;

  private final Algorithm algorithm;

  public ItemFinderService(List<Item> items, Package aPackage, Algorithm algorithm) {
    this.items = items;
    this.aPackage = aPackage;
    this.algorithm = algorithm;
  }

  @Override
  public List<Item> getResult() {
    return algorithm.execute(aPackage, items);
  }
}
