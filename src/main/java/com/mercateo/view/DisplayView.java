package com.mercateo.view;

import com.mercateo.model.Entity;

import java.util.List;

public interface DisplayView<U> {

  void display( final List<U> u);
}
