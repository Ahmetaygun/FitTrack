package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.dao.ClientDAO;
import org.example.model.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientDAOImpl implements ClientDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Client client) {
        entityManager.persist(client);
    }

    @Override
    @Transactional
    public Optional<Client> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Client.class, id));
    }

    @Override
    @Transactional
    public List<Client> findAll() {
        TypedQuery<Client> query = entityManager.createQuery("SELECT c FROM Client c", Client.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Client client) {
        entityManager.merge(client);
    }

    @Override
    @Transactional
    public void delete(Client client) {
        entityManager.remove(entityManager.contains(client) ? client : entityManager.merge(client));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id).ifPresent(this::delete);
    }

    @Override
    @Transactional
    public Optional<Client> findByUsername(String username) {
        TypedQuery<Client> query = entityManager.createQuery(
            "SELECT c FROM Client c WHERE c.username = :username", Client.class);
        query.setParameter("username", username);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<Client> findByEmail(String email) {
        TypedQuery<Client> query = entityManager.createQuery(
            "SELECT c FROM Client c WHERE c.email = :email", Client.class);
        query.setParameter("email", email);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Client> findByName(String name) {
        TypedQuery<Client> query = entityManager.createQuery(
            "SELECT c FROM Client c WHERE c.name LIKE :name", Client.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public List<Client> findByDietitianId(Long dietitianId) {
        TypedQuery<Client> query = entityManager.createQuery(
            "SELECT c FROM Client c WHERE c.dietitian.id = :dietitianId", Client.class);
        query.setParameter("dietitianId", dietitianId);
        return query.getResultList();
    }
} 