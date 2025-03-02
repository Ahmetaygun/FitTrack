package org.example.repository;

import org.example.model.DietType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietTypeRepository extends JpaRepository<DietType, Long> {
    // Diyet tipi adı ile sorgu yapmak için
    DietType findByName(String name);
}
