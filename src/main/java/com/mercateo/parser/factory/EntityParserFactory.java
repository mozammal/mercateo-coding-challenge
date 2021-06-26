package com.mercateo.parser.factory;

import com.mercateo.parser.FileReader;
import com.mercateo.parser.entity.EntityParser;
import com.mercateo.parser.entity.EntityScanner;
import com.mercateo.parser.Parser;

public class EntityParserFactory {

  public static Parser createParser(String type, FileReader fileReader) {
    if (type.equalsIgnoreCase("top-down")) {
      return new EntityParser(new EntityScanner(fileReader));
    }
    return null;
  }
}
