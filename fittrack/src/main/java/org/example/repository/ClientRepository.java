package org.example.repository;

import org.example.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Danışana özel sorgular eklenebilir
    Client findByEmail(String email);  // E-posta ile danışan arama
}
