package com.mercateo;

import com.mercateo.model.Item;
import com.mercateo.parser.entity.EntityParser;
import com.mercateo.parser.entity.EntityScanner;
import com.mercateo.parser.FileReader;
import com.mercateo.model.Package;
import com.mercateo.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainController {

  private ParserService parserService;

  private List<Package> packages;

  public MainController(String fileName) {
    this.parserService = new EntityParserService();
    this.packages = (List<Package>) parserService.getEntityFrom(fileName);
  }

  public void runApp() {

    for (Package aPackage : packages) {
      Algorithm algorithm = new BruteForceSearch();
      PackageSolver packageSolver = new BruteForcePackageSolver(aPackage, algorithm);
      List<Item> result = packageSolver.getResult();
      StringBuilder sb = new StringBuilder();
      for (Item item : result) {
        sb.append(item.getId()).append(",");
      }
      if (sb.length() == 0) {
        System.out.println("-");
      } else {
        System.out.println(sb.substring(0, sb.length() - 1));
      }
    }
  }

  public static void main(String... args) {
    String pathName = "E:/mercateo-coding-challenge/sampleInput.txt";

    MainController mainController = new MainController(pathName);
    mainController.runApp();

    /*
    try (BufferedReader fileReader =
        new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(pathName))))) {
      EntityParser entityParser = new EntityParser(new EntityScanner(new FileReader(fileReader)));
      List<Package> items = (List<Package>) entityParser.parse();
      items.forEach(System.out::println);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }*/
  }
}
