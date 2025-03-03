package org.example.controller;
import org.example.model.Analiz;
import org.example.service.AnalizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/analizler")
public class AnalizController {
    @Autowired
    private AnalizService analizService;

    @GetMapping
    public List<Analiz> getAllAnalizler() {
        return analizService.getAllAnalizler();
    }

    @GetMapping("/{id}")
    public Optional<Analiz> getAnalizById(@PathVariable Long id) {
        return analizService.getAnalizById(id);
    }

    @PostMapping
    public Analiz createAnaliz(@RequestBody Analiz analiz) {
        return analizService.saveAnaliz(analiz);
    }
}
