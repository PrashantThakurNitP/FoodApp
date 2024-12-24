package prashant.thakur.redis_spring_boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prashant.thakur.redis_spring_boot.model.Food;
import prashant.thakur.redis_spring_boot.service.FoodService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/food")
@Slf4j
public class FoodController {

    @Autowired FoodService foodService;

    @GetMapping("/{foodId}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long foodId) {
        log.info("Request to get food with id {}", foodId);
        Optional<Food> food = foodService.getFoodById(foodId);

        return food.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Food> getAllFood() {
        return foodService.getAllFoods();
    }

    @PostMapping
    public Food createFood(@RequestBody Food food) {
        log.info("Request to create food  {}", food);
        return foodService.saveFood(food);
    }

    @PutMapping("/{foodId}")
    public ResponseEntity<Food> updateFood(@PathVariable Long foodId, @RequestBody Food food) {
        Optional<Food> existingFood = foodService.getFoodById(foodId);

        if (existingFood.isPresent()) {
            food.setId(foodId);
            return ResponseEntity.ok(foodService.saveFood(food));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{foodId}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long foodId) {
        Optional<Food> existingFood = foodService.getFoodById(foodId);

        if (existingFood.isPresent()) {
            foodService.deleteFood(foodId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
