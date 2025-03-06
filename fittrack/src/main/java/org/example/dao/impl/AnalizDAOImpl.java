package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.dao.AnalizDAO;
import org.example.model.Analiz;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class AnalizDAOImpl implements AnalizDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Analiz analiz) {
        entityManager.persist(analiz);
    }

    @Override
    @Transactional
    public Optional<Analiz> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Analiz.class, id));
    }

    @Override
    public List<Analiz> findAll() {
        TypedQuery<Analiz> query = entityManager.createQuery("SELECT a FROM Analiz a", Analiz.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void delete(Analiz analiz) {
        entityManager.remove(entityManager.contains(analiz) ? analiz : entityManager.merge(analiz));
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
    }

    @Override
    @Transactional
    public void update(Analiz analiz) {
        entityManager.merge(analiz);
    }

    @Override
    public List<Analiz> findByClientId(Long clientId) {
        TypedQuery<Analiz> query = entityManager.createQuery(
            "SELECT a FROM Analiz a WHERE a.client.id = :clientId", Analiz.class);
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }

    @Override
    public List<Analiz> findByDate(LocalDate date) {
        TypedQuery<Analiz> query = entityManager.createQuery(
            "SELECT a FROM Analiz a WHERE a.date = :date", Analiz.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<Analiz> findByDateRange(LocalDate startDate, LocalDate endDate) {
        TypedQuery<Analiz> query = entityManager.createQuery(
            "SELECT a FROM Analiz a WHERE a.date BETWEEN :startDate AND :endDate", Analiz.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
} 