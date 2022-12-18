package me.agronaut.foodDatabase.repository;

import me.agronaut.foodDatabase.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodRepository extends MongoRepository<Food, Long> {
}
