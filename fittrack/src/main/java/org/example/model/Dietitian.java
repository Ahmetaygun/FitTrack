package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dietitians")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dietitian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
}
