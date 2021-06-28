package com.mercateo.service;

import com.mercateo.model.Entity;
import com.mercateo.parser.FileReader;
import com.mercateo.parser.Parser;
import com.mercateo.parser.factory.EntityParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.mercateo.config.Config.*;

/**
 *
 *
 * <h1>EntityDeserializerService</h1>
 *
 * <p>This class is responsible for deserializing packages and items from the source file
 */
public class EntityDeserializerService implements DeserializerService {

  private String pathName;

  public EntityDeserializerService(String pathName) {
    this.pathName = pathName;
  }

  /** deserializes packages and items from the source file */
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
            : new BufferedReader(new InputStreamReader(inputStream, UTF))) {
      Parser entityDeserializerParser =
          EntityParserFactory.createParser(new FileReader(fileReader), ENTITY_DESERIALIZER);
      List<? extends Entity> entities = entityDeserializerParser.parse();
      return entities;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
