import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodItemService {
    
    @Autowired
    private FoodItemRepository foodItemRepository;

    public FoodItem createFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public FoodItem getFoodItemById(Integer id) {
        return foodItemRepository.findById(id).orElse(null);
    }

    // Diğer besin işlemleri metotları
} 