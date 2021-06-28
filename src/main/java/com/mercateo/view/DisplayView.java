package com.mercateo.view;

import java.util.List;

/**
 *
 *
 * <h1>DisplayView</h1>
 *
 * <p>Marker interface for view
 */
public interface DisplayView<U> {

  void display(final List<U> u);
}
