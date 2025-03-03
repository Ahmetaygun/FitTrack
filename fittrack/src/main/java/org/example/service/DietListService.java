package org.example.service;

import org.example.model.DietList;
import org.example.repository.DietListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietListService {

    private final DietListRepository dietListRepository;

    @Autowired
    public DietListService(DietListRepository dietListRepository) {
        this.dietListRepository = dietListRepository;
    }

    // Danışanın ID'sine göre diyet listeleri almak
    public List<DietList> getDietListsByClientId(Long clientId) {
        return dietListRepository.findByClientId(clientId);
    }

    // Diyet tipi ID'sine göre diyet listeleri almak
    public List<DietList> getDietListsByDietTypeId(Long dietTypeId) {
        return dietListRepository.findByDietTypeId(dietTypeId);
    }

    // Diyetisyen ID'sine göre diyet listeleri almak
    public List<DietList> getDietListsByDietitianId(Long dietitianId) {
        return dietListRepository.findByDietitianId(dietitianId);
    }

    // Diyet listesi oluşturma
    public DietList createDietList(DietList dietList) {
        return dietListRepository.save(dietList);
    }

    // Diyet listesi güncelleme
    public DietList updateDietList(Long id, DietList updatedDietList) {
        updatedDietList.setId(id);
        return dietListRepository.save(updatedDietList);
    }
}
