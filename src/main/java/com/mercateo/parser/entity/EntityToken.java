package com.mercateo.parser.entity;

import com.mercateo.parser.FileReader;
import com.mercateo.parser.Token;

/**
 * <h1>EntityToken</h1>
 * <p>Base class for Entity token classes</p>
 */
public class EntityToken extends Token {

  public EntityToken(FileReader fileReader) {
    super(fileReader);
  }
}
