package org.example.service;

import org.example.model.Analiz;
import org.example.dao.AnalizDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AnalizService {

    private final AnalizDAO analizDAO;

    @Autowired
    public AnalizService(AnalizDAO analizDAO) {
        this.analizDAO = analizDAO;
    }

    // Analiz kaydetme
    public void saveAnaliz(Analiz analiz) {
        analizDAO.save(analiz);
    }

    // Tüm analizleri getirme
    public List<Analiz> getAllAnaliz() {
        return analizDAO.findAll();
    }

    // Analiz güncelleme
    public void updateAnaliz(Long analizId, Analiz analizDetails) {
        Optional<Analiz> existingAnaliz = analizDAO.findById(analizId);
        if (existingAnaliz.isPresent()) {
            Analiz updatedAnaliz = existingAnaliz.get();
            updatedAnaliz.setDate(analizDetails.getDate());
            updatedAnaliz.setWeight(analizDetails.getWeight());
            updatedAnaliz.setHeight(analizDetails.getHeight());
            updatedAnaliz.setBmi(analizDetails.getBmi());
            analizDAO.update(updatedAnaliz);
        }
    }

    // ID ile analiz getirme
    public Optional<Analiz> getAnalizById(Long analizId) {
        return analizDAO.findById(analizId);
    }

    // Danışan ID'sine göre analizleri getirme
    public List<Analiz> getAnalizByClientId(Long clientId) {
        return analizDAO.findByClientId(clientId);
    }

    // Tarihe göre analizleri getirme
    public List<Analiz> getAnalizByDate(LocalDate date) {
        return analizDAO.findByDate(date);
    }

    // Tarih aralığına göre analizleri getirme
    public List<Analiz> getAnalizByDateRange(LocalDate startDate, LocalDate endDate) {
        return analizDAO.findByDateRange(startDate, endDate);
    }

    // Analiz silme
    public void deleteAnaliz(Long analizId) {
        analizDAO.deleteById(analizId);
    }
}
