package com.mercateo.parser.entity;

import com.mercateo.model.Entity;
import com.mercateo.model.Item;
import com.mercateo.model.Package;
import com.mercateo.parser.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * <h1>EntityDeserializer</h1>
 *
 * <p>Entity deserializer for deserializing the items and packages from the file
 */
public class EntityDeserializer extends Parser {

  private PackageDeserializer packageDeserializer;

  private ItemsDeserializer itemsDeserializer;

  private Token token;

  public EntityDeserializer(Scanner scanner) {
    super(scanner);
    this.packageDeserializer = new PackageDeserializer(scanner);
    this.itemsDeserializer = new ItemsDeserializer(scanner);
  }

  /**
   * Parse the source file and generate packages and items
   */
  public List<? extends Entity> parse() {
    List<Entity> packages = new ArrayList<>();

    // iterate each token until the end of file
    while (!((token = nextToken()) instanceof EofToken)) {

      // is there a new line?
      // then do nothing, new line is used to start a new package and items
      if (token instanceof EofLineToken) {
        continue;
      }

      // set current token for package deserializer
      packageDeserializer.setToken(getToken());
      Package aPackage = ((List<Package>) packageDeserializer.parse()).get(0); // get Package from the file string
      List<Item> items = ((List<Item>) itemsDeserializer.parse());            // get items from the file string
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
