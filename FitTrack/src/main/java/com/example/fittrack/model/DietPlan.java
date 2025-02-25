import javax.persistence.*;
import java.util.Date;

@Entity
public class DietPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Patient patient;

    private String content;
    private Date creationDate;

    // Getter ve Setter metotları
} 