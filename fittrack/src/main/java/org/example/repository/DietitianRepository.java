package org.example.repository;


import org.example.model.Dietitian;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DietitianRepository extends JpaRepository<Dietitian, Long> {
    Optional<Dietitian> findByEmail(String email);
}
