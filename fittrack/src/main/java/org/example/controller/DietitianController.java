package org.example.controller;

import org.example.model.Dietitian;
import org.example.service.DietitianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dietitian")
public class DietitianController {

    private final DietitianService dietitianService;

    @Autowired
    public DietitianController(DietitianService dietitianService) {
        this.dietitianService = dietitianService;
    }

    // Yeni diyetisyen kaydı
    @PostMapping
    public ResponseEntity<Void> registerDietitian(@RequestBody Dietitian dietitian) {
        dietitianService.registerDietitian(dietitian);
        return ResponseEntity.ok().build();
    }

    // Tüm diyetisyenleri listeleme
    @GetMapping
    public ResponseEntity<List<Dietitian>> getAllDietitians() {
        List<Dietitian> dietitians = dietitianService.getAllDietitians();
        return ResponseEntity.ok(dietitians);
    }

    // ID ile diyetisyen getirme
    @GetMapping("/{id}")
    public ResponseEntity<Dietitian> getDietitianById(@PathVariable Long id) {
        Optional<Dietitian> dietitian = dietitianService.getDietitianById(id);
        return dietitian.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Diyetisyen silme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDietitian(@PathVariable Long id) {
        dietitianService.deleteDietitian(id);
        return ResponseEntity.ok().build();
    }
}
