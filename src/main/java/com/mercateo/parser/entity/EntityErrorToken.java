package com.mercateo.parser.entity;

import com.mercateo.parser.FileReader;

import static com.mercateo.parser.entity.EntityTokenType.ERROR;

/**
 *
 *
 * <h1>EntityErrorToken</h1>
 *
 * <p>Entity error token.
 */
public class EntityErrorToken extends EntityToken {
  public EntityErrorToken(FileReader fileReader) {
    super(fileReader);
    this.type = ERROR;
  }

  /** Do nothing */
  protected void extract() {}
}
