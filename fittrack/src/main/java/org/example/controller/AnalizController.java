package org.example.controller;

import org.example.model.Analiz;
import org.example.service.AnalizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/analiz")
public class AnalizController {

    private final AnalizService analizService;

    @Autowired
    public AnalizController(AnalizService analizService) {
        this.analizService = analizService;
    }

    // Yeni analiz oluşturma
    @PostMapping
    public ResponseEntity<Void> createAnaliz(@RequestBody Analiz analiz) {
        analizService.saveAnaliz(analiz);
        return ResponseEntity.ok().build();
    }

    // Analiz güncelleme
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAnaliz(@PathVariable Long id, @RequestBody Analiz analizDetails) {
        analizService.updateAnaliz(id, analizDetails);
        return ResponseEntity.ok().build();
    }

    // Tüm analizleri getirme
    @GetMapping
    public ResponseEntity<List<Analiz>> getAllAnaliz() {
        List<Analiz> analizler = analizService.getAllAnaliz();
        return ResponseEntity.ok(analizler);
    }

    // ID ile analiz getirme
    @GetMapping("/{id}")
    public ResponseEntity<Analiz> getAnalizById(@PathVariable Long id) {
        Optional<Analiz> analiz = analizService.getAnalizById(id);
        return analiz.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Danışan ID'sine göre analizleri getirme
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Analiz>> getAnalizByClientId(@PathVariable Long clientId) {
        List<Analiz> analizler = analizService.getAnalizByClientId(clientId);
        return ResponseEntity.ok(analizler);
    }

    // Tarihe göre analizleri getirme
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Analiz>> getAnalizByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Analiz> analizler = analizService.getAnalizByDate(date);
        return ResponseEntity.ok(analizler);
    }

    // Tarih aralığına göre analizleri getirme
    @GetMapping("/date-range")
    public ResponseEntity<List<Analiz>> getAnalizByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Analiz> analizler = analizService.getAnalizByDateRange(startDate, endDate);
        return ResponseEntity.ok(analizler);
    }

    // Analiz silme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnaliz(@PathVariable Long id) {
        analizService.deleteAnaliz(id);
        return ResponseEntity.ok().build();
    }
}
