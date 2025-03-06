package org.example.repository;

import org.example.model.DietList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DietListRepository extends JpaRepository<DietList, Long> {
    // Danışan ID'sine göre diyet listesi almak
    List<DietList> findByClientId(Long clientId);

    // Diyet tipi ID'sine göre diyet listesi almak
    List<DietList> findByDietTypeId(Long dietTypeId);

    // Diyetisyen ID'sine göre diyet listesi almak
    List<DietList> findByDietitianId(Long dietitianId);

    // Diyet tipine göre diyet listesi almak
    List<DietList> findByDietType(String dietType);
}
