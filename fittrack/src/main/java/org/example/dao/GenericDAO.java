package org.example.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T, ID> {
    void save(T entity);
    Optional<T> findById(ID id);
    void update(T entity);
    void delete(T entity);
    void deleteById(ID id);
    List<T> findAll();
} 