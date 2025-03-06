package org.example.dao;

import org.example.model.Client;
import java.util.List;
import java.util.Optional;

public interface ClientDAO extends GenericDAO<Client, Long> {
    Optional<Client> findByUsername(String username);
    Optional<Client> findByEmail(String email);
    List<Client> findByName(String name);
    List<Client> findByDietitianId(Long dietitianId);
} 