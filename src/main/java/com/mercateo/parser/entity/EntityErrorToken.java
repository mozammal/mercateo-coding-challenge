package com.mercateo.parser.entity;

import com.mercateo.parser.FileReader;

import static com.mercateo.parser.entity.EntityTokenType.ERROR;

public class EntityErrorToken extends EntityToken {
  public EntityErrorToken(FileReader fileReader) {
    super(fileReader);
    this.type = ERROR;
  }

  protected void extract() {}
}
