package com.mercateo.service;

import java.util.List;

public interface Algorithm<U, V> {

  List<V> execute(U aPackage, List<V> item);
}
