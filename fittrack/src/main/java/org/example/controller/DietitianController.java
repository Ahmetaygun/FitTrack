package org.example.controller;

import org.example.model.Dietitian;
import org.example.service.DietitianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dietitians")
public class DietitianController {

    private final DietitianService dietitianService;

    @Autowired
    public DietitianController(DietitianService dietitianService) {
        this.dietitianService = dietitianService;
    }

    // Diyetisyen Kaydı
    @PostMapping("/register")
    public ResponseEntity<Dietitian> registerDietitian(@RequestBody Dietitian dietitian) {
        return ResponseEntity.ok(dietitianService.registerDietitian(dietitian));
    }

    // Tüm Diyetisyenleri Getir
    @GetMapping
    public ResponseEntity<List<Dietitian>> getAllDietitians() {
        return ResponseEntity.ok(dietitianService.getAllDietitians());
    }

    // Belirli ID ile Diyetisyen Getir
    @GetMapping("/{id}")
    public ResponseEntity<Dietitian> getDietitianById(@PathVariable Long id) {
        return ResponseEntity.ok(dietitianService.getDietitianById(id));
    }

    // Diyetisyen Silme
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDietitian(@PathVariable Long id) {
        dietitianService.deleteDietitian(id);
        return ResponseEntity.ok("Diyetisyen başarıyla silindi!");
    }
}
