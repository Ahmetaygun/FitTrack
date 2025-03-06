package org.example.dao;

import org.example.model.Dietitian;
import java.util.List;
import java.util.Optional;

public interface DietitianDAO extends GenericDAO<Dietitian, Long> {
    Optional<Dietitian> findByUsername(String username);
    Optional<Dietitian> findByEmail(String email);
    List<Dietitian> findByName(String name);
    List<Dietitian> findBySpecialization(String specialization);
} 