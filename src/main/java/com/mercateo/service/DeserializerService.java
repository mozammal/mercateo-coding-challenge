package com.mercateo.service;

import com.mercateo.exception.FileNotFoundException;
import com.mercateo.model.Entity;

import java.io.File;
import java.util.List;

public interface DeserializerService {

  List<? extends Entity> getEntity();

  default boolean isAbsolutePath(String pathName) {
    File file = new File(pathName);
    if (!file.exists()) {
      throw new FileNotFoundException("file not found");
    }
    return true;
  }
}
