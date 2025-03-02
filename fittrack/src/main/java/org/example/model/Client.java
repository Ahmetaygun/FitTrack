package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email; // Danışanın e-posta adresi

    @Column(nullable = false)
    private String password; // Danışanın şifresi

    @Column(nullable = false)
    private String firstName; // Danışanın adı

    @Column(nullable = false)
    private String lastName; // Danışanın soyadı

    @Column(nullable = false)
    private String healthInfo; // Danışanın sağlık bilgileri (kan değerleri, ilaçlar, alerjiler vs.)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dietitian_id", nullable = false)
    private Dietitian dietitian; // Danışanın seçtiği diyetisyen

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diet_type_id", nullable = false)
    private DietType dietType; // Danışanın seçtiği diyet tipi

    @Column(nullable = false)
    private boolean isActive; // Danışanın aktif olup olmadığını belirtir

    @Column(nullable = false)
    private LocalDateTime createdAt; // Danışanın sisteme kayıt olduğu tarih
}
