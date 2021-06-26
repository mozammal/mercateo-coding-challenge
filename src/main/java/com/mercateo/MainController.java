package com.mercateo;

import com.mercateo.parser.entity.EntityParser;
import com.mercateo.parser.entity.EntityScanner;
import com.mercateo.parser.FileReader;
import com.mercateo.model.Package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainController {

  public static void main(String... args) {
    String pathName = "E:/mercateo-coding-challenge/sampleInput.txt";
    try (BufferedReader fileReader =
        new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(pathName))))) {
      EntityParser entityParser = new EntityParser(new EntityScanner(new FileReader(fileReader)));
      List<Package> items = (List<Package>) entityParser.parse();
      items.forEach(System.out::println);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
