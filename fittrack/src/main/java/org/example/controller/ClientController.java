package org.example.controller;

import org.example.model.Client;
import org.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Yeni danışan oluşturma
    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody Client client) {
        clientService.saveClient(client);
        return ResponseEntity.ok().build();
    }

    // Danışan güncelleme
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        clientService.updateClient(id, clientDetails);
        return ResponseEntity.ok().build();
    }

    // Tüm danışanları listeleme
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    // ID ile danışan getirme
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // İsim ile danışan arama
    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<Client>> findClientsByName(@PathVariable String name) {
        List<Client> clients = clientService.findClientsByName(name);
        return ResponseEntity.ok(clients);
    }

    // Diyetisyen ID'sine göre danışanları getirme
    @GetMapping("/dietitian/{dietitianId}")
    public ResponseEntity<List<Client>> findClientsByDietitian(@PathVariable Long dietitianId) {
        List<Client> clients = clientService.findClientsByDietitian(dietitianId);
        return ResponseEntity.ok(clients);
    }

    // Danışan silme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
}
