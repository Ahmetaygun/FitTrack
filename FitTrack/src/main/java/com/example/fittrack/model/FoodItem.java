import javax.persistence.*;

@Entity
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double carbohydrate;
    private Double fat;

    // Getter ve Setter metotları
} 