package com.mercateo.service;

import com.mercateo.model.Entity;
import com.mercateo.model.Package;

import java.util.List;

public interface ParserService {

  List<? extends Entity> getEntityFrom(String pathName);
}
