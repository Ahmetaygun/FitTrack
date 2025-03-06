package org.example.dao;

import org.example.model.DietList;
import java.util.List;
import java.util.Optional;

public interface DietListDAO extends GenericDAO<DietList, Long> {
    List<DietList> findByClientId(Long clientId);
    List<DietList> findByDietitianId(Long dietitianId);
    List<DietList> findByDietType(String dietType);
} 