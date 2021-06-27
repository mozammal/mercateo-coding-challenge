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

  private DisplayView displayView;

  public MainController(String fileName, DisplayView displayView) {
    this.parserService = new EntityParserService(fileName);
    this.packages = (List<Package>) parserService.getEntity();
    this.displayView = displayView;
  }

  public void renderOnConsoleView(List<Item> items) {
    displayView.display(items);
  }

  public void runApp() {

    for (Package aPackage : packages) {
      Algorithm algorithm = new BruteForceSearch();
      PackageSolver packageSolver = new BruteForcePackageSolver(aPackage, algorithm);
      List<Item> result = (List<Item>) packageSolver.getResult();
      renderOnConsoleView(result);
    }
  }

  public static void main(String[] args) {
    Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
    if (args == null || args.length == 0) {
      throw new FileNotFoundException("file not found");
    }
    MainController mainController = new MainController(args[0], new ConsoleDisplayView());
    mainController.runApp();
  }
}
