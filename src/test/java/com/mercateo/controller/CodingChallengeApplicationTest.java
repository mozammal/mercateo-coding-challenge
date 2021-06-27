package com.mercateo.controller;

import com.mercateo.exception.InvalidTokenException;
import com.mercateo.model.*;
import com.mercateo.model.Package;
import com.mercateo.parser.FileReader;
import com.mercateo.parser.Parser;
import com.mercateo.parser.factory.EntityParserFactory;
import com.mercateo.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import static com.mercateo.config.Config.*;

public class CodingChallengeApplicationTest {

  private Package aPackage;
  private List<Item> items;
  private List<String> attrs;
  private DeserializerService mockService;
  private String srcFile;

  @BeforeEach
  void beforeEach() {
    this.items =
        Arrays.asList(
            new Item(
                1,
                Double.valueOf("15.3"),
                Money.money(new BigDecimal("34"), Currency.getInstance(USD))),
            new Item(
                2,
                Double.valueOf("7.3"),
                Money.money(new BigDecimal("12"), Currency.getInstance(USD))));

    this.aPackage = new Package(Double.valueOf("8"), items);
    this.attrs = Arrays.asList("1", "15.3", "$", "34");
    this.mockService = mock(DeserializerService.class);
    this.srcFile = "sampleInput.txt";
  }

  @Test
  public void appShouldPassSampleTestProvidedWithAssignment() {
    StringBuilder expectedResult = new StringBuilder();
    expectedResult
        .append("4")
        .append("\n")
        .append("-")
        .append("\n")
        .append("2,7")
        .append("\n")
        .append("8,9")
        .append("\n");
    StringBuilder consoleOutPut = new StringBuilder();
    MainController mainController =
        new MainController(this.srcFile, new TestConsoleDisplayView(consoleOutPut));
    mainController.runApp();
    assertEquals(expectedResult.toString(), consoleOutPut.toString());
  }

  @Test
  public void deserializerParserShouldReturnNonEmptyListIfValidInput() {
    try (BufferedReader fileReader =
        new BufferedReader(new InputStreamReader(readFileFromClassPath(), "UTF-8"))) {
      Parser parser =
          EntityParserFactory.createParser(new FileReader(fileReader), ENTITY_DESERIALIZER);
      parser.parse();
    } catch (Exception e) {
      throw new RuntimeException("test failed");
    }
  }

  @Test
  public void deserializerParserShouldReturnExceptionIfInvalidInput() {
    this.srcFile = "invalidInput.txt";
    assertThrows(
        InvalidTokenException.class,
        () -> {
          try (BufferedReader fileReader =
              new BufferedReader(new InputStreamReader(readFileFromClassPath(), UTF))) {
            Parser parser =
                EntityParserFactory.createParser(new FileReader(fileReader), ENTITY_DESERIALIZER);
            parser.parse();
          }
        });
  }

  private InputStream readFileFromClassPath() {
    final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(srcFile);
    return inputStream;
  }

  @Test
  public void parserServiceShouldReturnSamePackage() {
    doReturn(Arrays.asList(aPackage)).when(mockService).getEntity();
    assertEquals(Arrays.asList(aPackage), mockService.getEntity());
  }

  @Test
  public void EntityFactoryShouldReturnPackageWhenProvidedProperty() {
    Package actualPackage =
        (Package) EntityFactory.create(Arrays.asList(String.valueOf(8)), PACKAGE);
    assertNotNull(actualPackage);
    assertEquals(aPackage.getCapacity(), actualPackage.getCapacity());
  }

  @Test
  public void EntityFactoryShouldReturnItemWhenProvidedProperty() {
    Item item = (Item) EntityFactory.create(attrs, ITEM);
    assertNotNull(item);
    assertEquals(items.get(0), item);
  }

  @Test
  public void BruteForceSolveReturnEmptyListWhenNoSolExists() {
    int expectedResultSize = 0;
    Algorithm algorithm = new BruteForceSearch();
    List<Item> items = aPackage.getItems();
    aPackage.setItems(items.subList(ZERO, ONE));
    PackagingSolverService packageSolver =
        new BruteForcePackagingSolverService(aPackage, algorithm);
    List<Item> result = (List<Item>) packageSolver.getResult();
    assertEquals(expectedResultSize, result.size());
  }

  @Test
  public void BruteForceSolveReturnResWhenSolExists() {
    Integer expectedItemId = 2;
    int expectedResultSize = 1;
    Algorithm algorithm = new BruteForceSearch();
    PackagingSolverService packageSolver =
        new BruteForcePackagingSolverService(aPackage, algorithm);
    List<Item> result = (List<Item>) packageSolver.getResult();
    assertEquals(expectedResultSize, result.size());
    assertEquals(expectedItemId, result.get(ZERO).getId());
  }
}
