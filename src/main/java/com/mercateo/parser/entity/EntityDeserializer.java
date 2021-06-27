package com.mercateo.parser.entity;

import com.mercateo.model.Entity;
import com.mercateo.model.Item;
import com.mercateo.model.Package;
import com.mercateo.parser.*;

import java.util.ArrayList;
import java.util.List;

public class EntityDeserializer extends Parser {

  private PackageDeserializer packageDeserializer;

  private ItemsDeserializer itemsDeserializer;

  private Token token;

  public EntityDeserializer(Scanner scanner) {
    super(scanner);
    this.packageDeserializer = new PackageDeserializer(scanner);
    this.itemsDeserializer = new ItemsDeserializer(scanner);
  }

  public List<? extends Entity> parse() {
    List<Entity> packages = new ArrayList<>();

    while (!((token = nextToken()) instanceof EofToken)) {
      if (token instanceof EofLineToken) {
        continue;
      }
      packageDeserializer.setToken(getToken());
      Package aPackage = ((List<Package>) packageDeserializer.parse()).get(0);
      List<Item> items = ((List<Item>) itemsDeserializer.parse());
      aPackage.setItems(items);
      packages.add(aPackage);
    }
    return packages;
  }

  public Token getToken() {
    return token;
  }

  public void setToken(Token token) {
    this.token = token;
  }
}
