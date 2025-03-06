package org.example.dao;

import org.example.model.Admin;
import java.util.List;
import java.util.Optional;

public interface AdminDAO extends GenericDAO<Admin, Long> {
    Optional<Admin> findByUsername(String username);
    Optional<Admin> findByEmail(String email);
    List<Admin> findBySuperAdmin(boolean isSuperAdmin);
    void updateLastLogin(Long adminId);
} 