package com.mercateo.service;

import com.mercateo.model.Entity;
import com.mercateo.parser.FileReader;
import com.mercateo.parser.Parser;
import com.mercateo.parser.entity.EntityDeserializerParser;
import com.mercateo.parser.entity.EntityScanner;
import com.mercateo.parser.factory.EntityParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EntityParserService implements ParserService {

  private String pathName;

  public EntityParserService(String pathName) {
    this.pathName = pathName;
  }

  @Override
  public List<? extends Entity> getEntity() {
    boolean absolutePath = false;
    final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(pathName);
    if (inputStream == null && isAbsolutePath(pathName)) {
      absolutePath = true;
    }

    try (BufferedReader fileReader =
        absolutePath
            ? new BufferedReader(
                new InputStreamReader(Files.newInputStream(Paths.get(pathName)), "UTF-8"))
            : new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
      Parser entityDeserializerParser =
          EntityParserFactory.createParser(new FileReader(fileReader), "entity-parser");
      List<? extends Entity> entities = entityDeserializerParser.parse();
      return entities;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
