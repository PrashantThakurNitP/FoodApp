package prashant.thakur.redis_spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import prashant.thakur.redis_spring_boot.model.Food;
import prashant.thakur.redis_spring_boot.repository.FoodRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired private FoodRepository foodRepository;

    @Cacheable(value = "foods", key = "#id")
    public Optional<Food> getFoodById(Long id) {
        return foodRepository.findById(id);
    }

    @CachePut(value = "foods", key = "#result.id") // uses id from response
    public Food saveFood(Food food) {
        food.setId(null);
        return foodRepository.save(food);
    }

    @CachePut(value = "foods", key = "#result.id") // uses id from response
    public Food updateFood(Food food) {
        return foodRepository.save(food);
    }



    @CacheEvict(value = "foods", key = "#id")
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }
}
