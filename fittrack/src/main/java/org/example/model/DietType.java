package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "diet_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DietType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name; // Diyet tipinin adı

    @Column(name = "description", columnDefinition = "TEXT")
    private String description; // Diyet tipi hakkında açıklama (isteğe bağlı)

    // Getter ve Setter metodları
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
