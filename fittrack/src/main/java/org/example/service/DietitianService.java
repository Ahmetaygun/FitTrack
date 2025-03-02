package org.example.service;

import org.example.model.Dietitian;
import org.example.repository.DietitianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietitianService {

    private final DietitianRepository dietitianRepository;

    @Autowired
    public DietitianService(DietitianRepository dietitianRepository) {
        this.dietitianRepository = dietitianRepository;
    }

    // Diyetisyen Kaydı
    public Dietitian registerDietitian(Dietitian dietitian) {
        return dietitianRepository.save(dietitian); // Şifreleme kaldırıldı
    }

    // Tüm Diyetisyenleri Listeleme
    public List<Dietitian> getAllDietitians() {
        return dietitianRepository.findAll();
    }

    // ID'ye göre Diyetisyen Getirme
    public Dietitian getDietitianById(Long id) {
        return dietitianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diyetisyen bulunamadı!"));
    }

    // Diyetisyen Silme
    public void deleteDietitian(Long id) {
        dietitianRepository.deleteById(id);
    }
}
