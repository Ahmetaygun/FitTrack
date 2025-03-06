package org.example.service;

import org.example.model.Client;
import org.example.dao.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientDAO clientDAO;

    @Autowired
    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    // Danışan kaydetme
    public void saveClient(Client client) {
        clientDAO.save(client);
    }

    // Danışanları listeleme
    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }

    // Danışan bilgilerini güncelleme
    public void updateClient(Long clientId, Client clientDetails) {
        Optional<Client> existingClient = clientDAO.findById(clientId);
        if (existingClient.isPresent()) {
            Client updatedClient = existingClient.get();
            updatedClient.setFirstName(clientDetails.getFirstName());
            updatedClient.setLastName(clientDetails.getLastName());
            updatedClient.setEmail(clientDetails.getEmail());
            updatedClient.setHealthInfo(clientDetails.getHealthInfo());
            clientDAO.update(updatedClient);
        }
    }

    // Danışan id ile getirme
    public Optional<Client> getClientById(Long clientId) {
        return clientDAO.findById(clientId);
    }

    // İsim ile danışan arama
    public List<Client> findClientsByName(String name) {
        return clientDAO.findByName(name);
    }

    // Diyetisyen ID'sine göre danışanları getirme
    public List<Client> findClientsByDietitian(Long dietitianId) {
        return clientDAO.findByDietitianId(dietitianId);
    }

    // Danışan silme
    public void deleteClient(Long clientId) {
        clientDAO.deleteById(clientId);
    }
}
