package org.example.controller;

import org.example.model.DietType;
import org.example.service.DietTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diet-type")
public class DietTypeController {

    private final DietTypeService dietTypeService;

    @Autowired
    public DietTypeController(DietTypeService dietTypeService) {
        this.dietTypeService = dietTypeService;
    }

    // Yeni diyet tipi oluşturma
    @PostMapping
    public ResponseEntity<Void> createDietType(@RequestBody DietType dietType) {
        dietTypeService.saveDietType(dietType);
        return ResponseEntity.ok().build();
    }

    // Tüm diyet tiplerini getirme
    @GetMapping
    public ResponseEntity<List<DietType>> getAllDietTypes() {
        List<DietType> dietTypes = dietTypeService.getAllDietTypes();
        return ResponseEntity.ok(dietTypes);
    }

    // ID ile diyet tipi getirme
    @GetMapping("/{id}")
    public ResponseEntity<DietType> getDietTypeById(@PathVariable Long id) {
        Optional<DietType> dietType = dietTypeService.getDietTypeById(id);
        return dietType.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Diyet tipi silme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDietType(@PathVariable Long id) {
        dietTypeService.deleteDietType(id);
        return ResponseEntity.ok().build();
    }
}
