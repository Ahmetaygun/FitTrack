package org.example.service;

import org.example.model.DietList;
import org.example.repository.DietListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DietListService {

    private final DietListRepository dietListRepository;

    @Autowired
    public DietListService(DietListRepository dietListRepository) {
        this.dietListRepository = dietListRepository;
    }

    // Diyet listesi kaydetme
    public void saveDietList(DietList dietList) {
        dietListRepository.save(dietList);
    }

    // Tüm diyet listelerini getirme
    public List<DietList> getAllDietLists() {
        return dietListRepository.findAll();
    }

    // Diyet listesi güncelleme
    public DietList updateDietList(Long dietListId, DietList dietListDetails) {
        Optional<DietList> existingDietList = dietListRepository.findById(dietListId);
        if (existingDietList.isPresent()) {
            DietList updatedDietList = existingDietList.get();
            updatedDietList.setDietType(dietListDetails.getDietType());
            updatedDietList.setStartDate(dietListDetails.getStartDate());
            updatedDietList.setEndDate(dietListDetails.getEndDate());
            updatedDietList.setDescription(dietListDetails.getDescription());
            return dietListRepository.save(updatedDietList);
        }
        return null;
    }

    // ID ile diyet listesi getirme
    public Optional<DietList> getDietListById(Long id) {
        return dietListRepository.findById(id);
    }

    // Danışan ID'sine göre diyet listelerini getirme
    public List<DietList> getDietListsByClientId(Long clientId) {
        return dietListRepository.findByClientId(clientId);
    }

    // Diyetisyen ID'sine göre diyet listelerini getirme
    public List<DietList> getDietListsByDietitianId(Long dietitianId) {
        return dietListRepository.findByDietitianId(dietitianId);
    }

    // Diyet tipine göre diyet listeleri getirme
    public List<DietList> getDietListsByDietType(String dietType) {
        return dietListRepository.findByDietType(dietType);
    }

    // Diyet listesi silme
    public void deleteDietList(Long id) {
        dietListRepository.deleteById(id);
    }
}
