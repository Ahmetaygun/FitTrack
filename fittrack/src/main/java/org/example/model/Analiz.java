package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Analiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double oncekiKilo;
    private double sonrakiKilo;
    private double oncekiBel;
    private double sonrakiBel;
    private double oncekiKanDegeri;
    private double sonrakiKanDegeri;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}