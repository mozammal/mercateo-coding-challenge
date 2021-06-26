package com.mercateo.service;

import com.mercateo.model.Entity;
import com.mercateo.model.Package;
import com.mercateo.parser.FileReader;
import com.mercateo.parser.entity.EntityParser;
import com.mercateo.parser.entity.EntityScanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EntityParserService implements ParserService {

  public EntityParserService() {}

  @Override
  public List<? extends Entity> getEntityFrom(String pathName) {
    try (BufferedReader fileReader =
        new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(pathName))))) {
      EntityParser entityParser = new EntityParser(new EntityScanner(new FileReader(fileReader)));
      List<? extends Entity> entities = entityParser.parse();
      return entities;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
