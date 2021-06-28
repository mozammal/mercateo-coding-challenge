package com.mercateo.controller;

import com.mercateo.algorithm.Algorithm;
import com.mercateo.algorithm.BruteForceSearch;
import com.mercateo.exception.ExceptionHandler;
import com.mercateo.exception.FileNotFoundException;
import com.mercateo.model.Item;
import com.mercateo.model.Package;
import com.mercateo.service.*;
import com.mercateo.view.ConsoleDisplayView;
import com.mercateo.view.DisplayView;

import java.util.List;

/**
 *
 *
 * <h1>MainController</h1>
 *
 * <p>This class triggers at startup time
 */
public class MainController {

  private DeserializerService deserializerService;

  private List<Package> packages;

  private DisplayView displayView;

  public MainController(String fileName, DisplayView displayView) {
    this.deserializerService = new EntityDeserializerService(fileName);
    this.packages = (List<Package>) deserializerService.getEntity();
    this.displayView = displayView;
  }

  public void renderOnConsoleView(List<Item> items) {
    displayView.display(items);
  }

  /**
   * this method glues between controller and teh rest of application. It retrieves items and
   * packages from the services, and display the result in teh console.
   */
  public void runApp() {
    for (Package aPackage : packages) {
      Algorithm algorithm = new BruteForceSearch();
      PackagingSolverService packageSolver =
          new BruteForcePackagingSolverService(aPackage, algorithm);
      List<Item> result = (List<Item>) packageSolver.getResult();
      renderOnConsoleView(result);
    }
  }

  /** this method kicks in at startup time */
  public static void main(String[] args) {
    Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
    if (args == null || args.length == 0) {
      throw new FileNotFoundException("file not found");
    }

    // create main controller with the provided file and a view
    MainController mainController = new MainController(args[0], new ConsoleDisplayView());

    // run the application
    mainController.runApp();
  }
}
