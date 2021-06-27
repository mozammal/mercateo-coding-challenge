package com.mercateo.controller;

import com.mercateo.model.Item;
import com.mercateo.view.DisplayView;

import java.util.List;

public class TestConsoleDisplayView implements DisplayView<Item> {
  private StringBuilder consoleResult;

  public TestConsoleDisplayView(StringBuilder consoleResult) {
    this.consoleResult = consoleResult;
  }

  @Override
  public void display(List<Item> items) {
    StringBuilder sb = new StringBuilder();
    for (Item item : items) {
      sb.append(item.getId()).append(",");
    }
    if (sb.length() == 0) {
      sb.append("-\n");
    } else {
      sb.setLength(sb.length() - 1);
      sb.append("\n");
    }
    consoleResult.append(sb);
  }
}
