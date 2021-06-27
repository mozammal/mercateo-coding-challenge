package com.mercateo.controller;

import com.mercateo.exception.ExceptionHandler;
import com.mercateo.exception.FileNotFoundException;
import com.mercateo.model.Item;
import com.mercateo.model.Package;
import com.mercateo.service.*;
import com.mercateo.view.ConsoleDisplayView;
import com.mercateo.view.DisplayView;

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

  public static void main(String[] args) {
    Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
    if (args == null || args.length == 0) {
      throw new FileNotFoundException("file not found");
    }
    MainController mainController = new MainController(args[0]);
    mainController.runApp();
  }
}
