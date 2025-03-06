package org.example.dao;

import org.example.model.Analiz;
import java.util.List;
import java.time.LocalDate;

public interface AnalizDAO extends GenericDAO<Analiz, Long> {
    List<Analiz> findByClientId(Long clientId);
    List<Analiz> findByDate(LocalDate date);
    List<Analiz> findByDateRange(LocalDate startDate, LocalDate endDate);
} 