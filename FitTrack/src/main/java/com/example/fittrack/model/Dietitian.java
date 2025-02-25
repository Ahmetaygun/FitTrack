import javax.persistence.*;

@Entity
public class Dietitian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private User user;

    private String specialization;

    // Getter ve Setter metotları
} 