package org.example.controller;

import org.example.model.DietList;
import org.example.service.DietListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diet-list")
public class DietListController {

    private final DietListService dietListService;

    @Autowired
    public DietListController(DietListService dietListService) {
        this.dietListService = dietListService;
    }

    // Yeni diyet listesi oluşturma
    @PostMapping
    public ResponseEntity<Void> createDietList(@RequestBody DietList dietList) {
        dietListService.saveDietList(dietList);
        return ResponseEntity.ok().build();
    }

    // Diyet listesi güncelleme
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDietList(@PathVariable Long id, @RequestBody DietList dietListDetails) {
        dietListService.updateDietList(id, dietListDetails);
        return ResponseEntity.ok().build();
    }

    // Tüm diyet listelerini getirme
    @GetMapping
    public ResponseEntity<List<DietList>> getAllDietLists() {
        List<DietList> dietLists = dietListService.getAllDietLists();
        return ResponseEntity.ok(dietLists);
    }

    // ID ile diyet listesi getirme
    @GetMapping("/{id}")
    public ResponseEntity<DietList> getDietListById(@PathVariable Long id) {
        Optional<DietList> dietList = dietListService.getDietListById(id);
        return dietList.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Danışan ID'sine göre diyet listelerini getirme
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<DietList>> getDietListsByClientId(@PathVariable Long clientId) {
        List<DietList> dietLists = dietListService.getDietListsByClientId(clientId);
        return ResponseEntity.ok(dietLists);
    }

    // Diyetisyen ID'sine göre diyet listelerini getirme
    @GetMapping("/dietitian/{dietitianId}")
    public ResponseEntity<List<DietList>> getDietListsByDietitianId(@PathVariable Long dietitianId) {
        List<DietList> dietLists = dietListService.getDietListsByDietitianId(dietitianId);
        return ResponseEntity.ok(dietLists);
    }

    // Diyet tipine göre diyet listelerini getirme
    @GetMapping("/diet-type/{dietType}")
    public ResponseEntity<List<DietList>> getDietListsByDietType(@PathVariable String dietType) {
        List<DietList> dietLists = dietListService.getDietListsByDietType(dietType);
        return ResponseEntity.ok(dietLists);
    }

    // Diyet listesi silme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDietList(@PathVariable Long id) {
        dietListService.deleteDietList(id);
        return ResponseEntity.ok().build();
    }
}
