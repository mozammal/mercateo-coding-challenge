package com.mercateo.parser.factory;

import com.mercateo.parser.FileReader;
import com.mercateo.parser.entity.EntityDeserializerParser;
import com.mercateo.parser.entity.EntityScanner;
import com.mercateo.parser.Parser;

public class EntityParserFactory {

  public static Parser createParser(FileReader fileReader, String type) {
    if (type.equalsIgnoreCase("entity-parser")) {
      return new EntityDeserializerParser(new EntityScanner(fileReader));
    }
    return null;
  }
}
