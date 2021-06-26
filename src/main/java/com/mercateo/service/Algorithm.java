package com.mercateo.service;

import java.util.List;

public interface Algorithm<U, V> {

  List<V> findSolution(U aPackage);
}
