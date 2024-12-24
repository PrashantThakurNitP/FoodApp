package prashant.thakur.redis_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prashant.thakur.redis_spring_boot.model.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
