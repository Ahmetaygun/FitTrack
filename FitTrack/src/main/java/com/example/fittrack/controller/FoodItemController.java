import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fooditems")
public class FoodItemController {
    
    @Autowired
    private FoodItemService foodItemService;

    @PostMapping("/add")
    public FoodItem addFoodItem(@RequestBody FoodItem foodItem) {
        return foodItemService.createFoodItem(foodItem);
    }

    @GetMapping("/{id}")
    public FoodItem getFoodItemById(@PathVariable Integer id) {
        return foodItemService.getFoodItemById(id);
    }

    // Diğer besin API uç noktaları
} 