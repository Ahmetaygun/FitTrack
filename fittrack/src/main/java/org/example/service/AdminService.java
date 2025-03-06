package org.example.service;

import org.example.model.Admin;
import org.example.dao.AdminDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminDAO adminDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminDAO adminDAO, PasswordEncoder passwordEncoder) {
        this.adminDAO = adminDAO;
        this.passwordEncoder = passwordEncoder;
    }

    // Admin kaydetme
    public Admin saveAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminDAO.save(admin);
        return admin;
    }

    // Admin girişi
    public Optional<Admin> login(String username, String password) {
        Optional<Admin> adminOpt = adminDAO.findByUsername(username);
        if (adminOpt.isPresent() && passwordEncoder.matches(password, adminOpt.get().getPassword())) {
            adminDAO.updateLastLogin(adminOpt.get().getId());
            return adminOpt;
        }
        return Optional.empty();
    }

    // Tüm adminleri listeleme
    public List<Admin> getAllAdmins() {
        return adminDAO.findAll();
    }

    // Admin güncelleme
    public Admin updateAdmin(Long adminId, Admin adminDetails) {
        Optional<Admin> existingAdmin = adminDAO.findById(adminId);
        if (existingAdmin.isPresent()) {
            Admin updatedAdmin = existingAdmin.get();
            updatedAdmin.setUsername(adminDetails.getUsername());
            updatedAdmin.setEmail(adminDetails.getEmail());
            updatedAdmin.setFullName(adminDetails.getFullName());
            updatedAdmin.setSuperAdmin(adminDetails.isSuperAdmin());
            
            // Şifre değişikliği varsa
            if (adminDetails.getPassword() != null && !adminDetails.getPassword().isEmpty()) {
                updatedAdmin.setPassword(passwordEncoder.encode(adminDetails.getPassword()));
            }
            
            adminDAO.update(updatedAdmin);
            return updatedAdmin;
        }
        return null;
    }

    // Admin ID ile getirme
    public Optional<Admin> getAdminById(Long adminId) {
        return adminDAO.findById(adminId);
    }

    // Kullanıcı adı ile admin arama
    public Optional<Admin> findAdminByUsername(String username) {
        return adminDAO.findByUsername(username);
    }

    // Email ile admin arama
    public Optional<Admin> findAdminByEmail(String email) {
        return adminDAO.findByEmail(email);
    }

    // Super admin listesi
    public List<Admin> findSuperAdmins() {
        return adminDAO.findBySuperAdmin(true);
    }

    // Admin silme
    public void deleteAdmin(Long adminId) {
        adminDAO.deleteById(adminId);
    }
} 