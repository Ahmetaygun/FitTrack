package org.example.service;

import org.example.model.Analiz;
import org.example.repository.AnalizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnalizService {
    @Autowired
    private AnalizRepository analizRepository;

    public List<Analiz> getAllAnalizler() {
        return analizRepository.findAll();
    }

    public Optional<Analiz> getAnalizById(Long id) {
        return analizRepository.findById(id);
    }

    public Analiz saveAnaliz(Analiz analiz) {
        return analizRepository.save(analiz);
    }
}
