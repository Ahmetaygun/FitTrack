package org.example.controller;

import org.example.model.Admin;
import org.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Admin girişi
    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestParam String username, @RequestParam String password) {
        Optional<Admin> admin = adminService.login(username, password);
        return admin.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // Yeni admin oluşturma
    @PostMapping
    public ResponseEntity<Void> createAdmin(@RequestBody Admin admin) {
        adminService.saveAdmin(admin);
        return ResponseEntity.ok().build();
    }

    // Admin güncelleme
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAdmin(@PathVariable Long id, @RequestBody Admin adminDetails) {
        adminService.updateAdmin(id, adminDetails);
        return ResponseEntity.ok().build();
    }

    // Tüm adminleri listeleme
    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    // ID ile admin getirme
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Optional<Admin> admin = adminService.getAdminById(id);
        return admin.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Kullanıcı adı ile admin arama
    @GetMapping("/search/username/{username}")
    public ResponseEntity<Admin> findAdminByUsername(@PathVariable String username) {
        Optional<Admin> admin = adminService.findAdminByUsername(username);
        return admin.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Email ile admin arama
    @GetMapping("/search/email/{email}")
    public ResponseEntity<Admin> findAdminByEmail(@PathVariable String email) {
        Optional<Admin> admin = adminService.findAdminByEmail(email);
        return admin.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Super adminleri listeleme
    @GetMapping("/super-admins")
    public ResponseEntity<List<Admin>> getSuperAdmins() {
        List<Admin> superAdmins = adminService.findSuperAdmins();
        return ResponseEntity.ok(superAdmins);
    }

    // Admin silme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.ok().build();
    }
} 