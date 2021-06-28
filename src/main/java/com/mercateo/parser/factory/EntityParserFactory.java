package com.mercateo.parser.factory;

import com.mercateo.parser.FileReader;
import com.mercateo.parser.entity.EntityDeserializer;
import com.mercateo.parser.entity.EntityScanner;
import com.mercateo.parser.Parser;

import static com.mercateo.config.Config.*;

/**
 *
 *
 * <h1>EntityParserFactory</h1>
 *
 * <p>Entity parser factory for creating parser from the source file
 */
public class EntityParserFactory {

  public static Parser createParser(FileReader fileReader, String type) {
    if (type.equalsIgnoreCase(ENTITY_DESERIALIZER)) {
      return new EntityDeserializer(new EntityScanner(fileReader));
    }
    throw new UnsupportedOperationException();
  }
}
