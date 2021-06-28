package com.mercateo.algorithm;

import java.util.List;

/**
 *
 *
 * <h1>Algorithm</h1>
 *
 * <p>Base interface for Algorithm used in this App
 */
public interface Algorithm<U, V> {

  List<V> findSolution(U aPackage);
}
