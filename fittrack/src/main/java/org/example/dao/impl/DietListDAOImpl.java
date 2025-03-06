package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.dao.DietListDAO;
import org.example.model.DietList;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class DietListDAOImpl implements DietListDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(DietList dietList) {
        entityManager.persist(dietList);
    }

    @Override
    @Transactional
    public Optional<DietList> findById(Long id) {
        return Optional.ofNullable(entityManager.find(DietList.class, id));
    }

    @Override
    @Transactional
    public List<DietList> findAll() {
        TypedQuery<DietList> query = entityManager.createQuery("SELECT d FROM DietList d", DietList.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(DietList dietList) {
        entityManager.merge(dietList);
    }

    @Override
    @Transactional
    public void delete(DietList dietList) {
        entityManager.remove(entityManager.contains(dietList) ? dietList : entityManager.merge(dietList));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id).ifPresent(this::delete);
    }

    @Override
    @Transactional
    public List<DietList> findByClientId(Long clientId) {
        TypedQuery<DietList> query = entityManager.createQuery(
            "SELECT d FROM DietList d WHERE d.client.id = :clientId", DietList.class);
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<DietList> findByDietitianId(Long dietitianId) {
        TypedQuery<DietList> query = entityManager.createQuery(
            "SELECT d FROM DietList d WHERE d.dietitian.id = :dietitianId", DietList.class);
        query.setParameter("dietitianId", dietitianId);
        return query.getResultList();
    }

    @Override
    public List<DietList> findByDietType(String dietType) {
        TypedQuery<DietList> query = entityManager.createQuery(
            "SELECT d FROM DietList d WHERE d.dietType = :dietType", DietList.class);
        query.setParameter("dietType", dietType);
        return query.getResultList();
    }
} 