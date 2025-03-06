package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.dao.DietitianDAO;
import org.example.model.Dietitian;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class DietitianDAOImpl implements DietitianDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Dietitian dietitian) {
        entityManager.persist(dietitian);
    }

    @Override
    @Transactional
    public Optional<Dietitian> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Dietitian.class, id));
    }

    @Override
    @Transactional
    public List<Dietitian> findAll() {
        TypedQuery<Dietitian> query = entityManager.createQuery("SELECT d FROM Dietitian d", Dietitian.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Dietitian dietitian) {
        entityManager.merge(dietitian);
    }

    @Override
    @Transactional
    public void delete(Dietitian dietitian) {
        entityManager.remove(entityManager.contains(dietitian) ? dietitian : entityManager.merge(dietitian));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id).ifPresent(this::delete);
    }

    @Override
    @Transactional
    public Optional<Dietitian> findByUsername(String username) {
        TypedQuery<Dietitian> query = entityManager.createQuery(
            "SELECT d FROM Dietitian d WHERE d.username = :username", Dietitian.class);
        query.setParameter("username", username);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<Dietitian> findByEmail(String email) {
        TypedQuery<Dietitian> query = entityManager.createQuery(
            "SELECT d FROM Dietitian d WHERE d.email = :email", Dietitian.class);
        query.setParameter("email", email);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public List<Dietitian> findByName(String name) {
        TypedQuery<Dietitian> query = entityManager.createQuery(
            "SELECT d FROM Dietitian d WHERE LOWER(d.firstName) LIKE LOWER(:name) OR LOWER(d.lastName) LIKE LOWER(:name)", 
            Dietitian.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Dietitian> findBySpecialization(String specialization) {
        TypedQuery<Dietitian> query = entityManager.createQuery(
            "SELECT d FROM Dietitian d WHERE LOWER(d.specialization) LIKE LOWER(:specialization)", 
            Dietitian.class);
        query.setParameter("specialization", "%" + specialization + "%");
        return query.getResultList();
    }
} 