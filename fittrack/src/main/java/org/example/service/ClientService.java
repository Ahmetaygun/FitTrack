package org.example.service;

import org.example.model.Client;
import org.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Danışan kaydetme
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    // Danışanları listeleme
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Danışan bilgilerini güncelleme
    public Client updateClient(Long clientId, Client clientDetails) {
        Optional<Client> existingClient = clientRepository.findById(clientId);
        if (existingClient.isPresent()) {
            Client updatedClient = existingClient.get();
            updatedClient.setFirstName(clientDetails.getFirstName());
            updatedClient.setEmail(clientDetails.getEmail());
            updatedClient.setHealthInfo(clientDetails.getHealthInfo());
            return clientRepository.save(updatedClient);
        } else {
            return null;  // Mevcut danışan bulunmazsa null döner
        }
    }

    // Danışan id ile getirme
    public Optional<Client> getClientById(Long clientId) {
        return clientRepository.findById(clientId);
    }

    // E-posta ile danışan getirme
    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
}
