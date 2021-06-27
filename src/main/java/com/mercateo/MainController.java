package com.mercateo;

import com.mercateo.model.Item;
import com.mercateo.parser.entity.EntityParser;
import com.mercateo.parser.entity.EntityScanner;
import com.mercateo.parser.FileReader;
import com.mercateo.model.Package;
import com.mercateo.service.*;
import com.mercateo.view.ConsoleDisplayView;
import com.mercateo.view.DisplayView;

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

  public void consoleDisplayView(List<Item> items) {
    DisplayView displayView = new ConsoleDisplayView();
    displayView.display(items);
  }

  public void runApp() {

    for (Package aPackage : packages) {
      Algorithm algorithm = new BruteForceSearch();
      PackageSolver packageSolver = new BruteForcePackageSolver(aPackage, algorithm);
      List<Item> result = (List<Item>) packageSolver.getResult();
      consoleDisplayView(result);
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
