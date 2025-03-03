package org.example.controller;

import org.example.model.DietList;
import org.example.service.DietListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diet-lists")
public class DietListController {

    private final DietListService dietListService;

    @Autowired
    public DietListController(DietListService dietListService) {
        this.dietListService = dietListService;
    }

    // Danışanın diyet listelerini almak
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<DietList>> getDietListsByClientId(@PathVariable Long clientId) {
        List<DietList> dietLists = dietListService.getDietListsByClientId(clientId);
        return ResponseEntity.ok(dietLists);
    }

    // Diyet tipi ID'sine göre diyet listeleri almak
    @GetMapping("/diet-type/{dietTypeId}")
    public ResponseEntity<List<DietList>> getDietListsByDietTypeId(@PathVariable Long dietTypeId) {
        List<DietList> dietLists = dietListService.getDietListsByDietTypeId(dietTypeId);
        return ResponseEntity.ok(dietLists);
    }

    // Diyetisyen ID'sine göre diyet listeleri almak
    @GetMapping("/dietitian/{dietitianId}")
    public ResponseEntity<List<DietList>> getDietListsByDietitianId(@PathVariable Long dietitianId) {
        List<DietList> dietLists = dietListService.getDietListsByDietitianId(dietitianId);
        return ResponseEntity.ok(dietLists);
    }

    // Diyet listesi oluşturma
    @PostMapping
    public ResponseEntity<DietList> createDietList(@RequestBody DietList dietList) {
        DietList createdDietList = dietListService.createDietList(dietList);
        return ResponseEntity.ok(createdDietList);
    }

    // Diyet listesi güncelleme
    @PutMapping("/{id}")
    public ResponseEntity<DietList> updateDietList(@PathVariable Long id, @RequestBody DietList updatedDietList) {
        DietList dietList = dietListService.updateDietList(id, updatedDietList);
        return ResponseEntity.ok(dietList);
    }
}
