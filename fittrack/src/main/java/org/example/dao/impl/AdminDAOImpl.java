package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.dao.AdminDAO;
import org.example.model.Admin;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AdminDAOImpl implements AdminDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Admin admin) {
        entityManager.persist(admin);
    }

    @Override
    @Transactional
    public Optional<Admin> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Admin.class, id));
    }

    @Override
    @Transactional
    public List<Admin> findAll() {
        TypedQuery<Admin> query = entityManager.createQuery("SELECT a FROM Admin a", Admin.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Admin admin) {
        entityManager.merge(admin);
    }

    @Override
    @Transactional
    public void delete(Admin admin) {
        entityManager.remove(entityManager.contains(admin) ? admin : entityManager.merge(admin));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id).ifPresent(this::delete);
    }

    @Override
    @Transactional
    public Optional<Admin> findByUsername(String username) {
        TypedQuery<Admin> query = entityManager.createQuery(
            "SELECT a FROM Admin a WHERE a.username = :username", Admin.class);
        query.setParameter("username", username);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<Admin> findByEmail(String email) {
        TypedQuery<Admin> query = entityManager.createQuery(
            "SELECT a FROM Admin a WHERE a.email = :email", Admin.class);
        query.setParameter("email", email);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Admin> findBySuperAdmin(boolean isSuperAdmin) {
        TypedQuery<Admin> query = entityManager.createQuery(
            "SELECT a FROM Admin a WHERE a.isSuperAdmin = :isSuperAdmin", Admin.class);
        query.setParameter("isSuperAdmin", isSuperAdmin);
        return query.getResultList();
    }

    @Override
    public void updateLastLogin(Long adminId) {
        entityManager.createQuery(
            "UPDATE Admin a SET a.lastLogin = :lastLogin WHERE a.id = :adminId")
            .setParameter("lastLogin", LocalDateTime.now())
            .setParameter("adminId", adminId)
            .executeUpdate();
    }
} 