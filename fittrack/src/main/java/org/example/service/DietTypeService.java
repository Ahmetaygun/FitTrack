package org.example.service;

import org.example.model.DietType;
import org.example.repository.DietTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DietTypeService {

    private final DietTypeRepository dietTypeRepository;

    @Autowired
    public DietTypeService(DietTypeRepository dietTypeRepository) {
        this.dietTypeRepository = dietTypeRepository;
    }

    // Diyet tipi kaydetme
    public void saveDietType(DietType dietType) {
        dietTypeRepository.save(dietType);
    }

    // Tüm diyet tiplerini getirme
    public List<DietType> getAllDietTypes() {
        return dietTypeRepository.findAll();
    }

    // ID ile diyet tipi getirme
    public Optional<DietType> getDietTypeById(Long id) {
        return dietTypeRepository.findById(id);
    }

    // Diyet tipi silme
    public void deleteDietType(Long id) {
        dietTypeRepository.deleteById(id);
    }
}
