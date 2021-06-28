package com.mercateo.service;

import com.mercateo.exception.FileNotFoundException;
import com.mercateo.model.Entity;

import java.io.File;
import java.util.List;

/**
 *
 *
 * <h1>DeserializerService</h1>
 *
 * <p>Marker interface for deserializing packages and items from the source file
 */
public interface DeserializerService {

  List<? extends Entity> getEntity();

  /** check if the path is absolute */
  default boolean isAbsolutePath(String pathName) {
    File file = new File(pathName);
    if (!file.exists()) {
      throw new FileNotFoundException("file not found");
    }
    return true;
  }
}
