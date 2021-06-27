package com.mercateo.view;

import com.mercateo.model.Item;

import java.util.List;

public class ConsoleDisplayView implements DisplayView<Item> {

  public ConsoleDisplayView() {}

  @Override
  public void display(List<Item> items) {
    StringBuilder sb = new StringBuilder();
    for (Item item : items) {
      sb.append(item.getId()).append(",");
    }
    if (sb.length() == 0) {
      System.out.printf("-%n%n");
    } else {
      System.out.printf("%s%n%n", sb.substring(0, sb.length() - 1));
    }
  }
}
