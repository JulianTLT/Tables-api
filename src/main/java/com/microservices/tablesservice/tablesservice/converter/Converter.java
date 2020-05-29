package com.microservices.tablesservice.tablesservice.converter;

public interface Converter<E,M> {
    M entityToModel(E entity);
    E modelToEntity(M model);
}
