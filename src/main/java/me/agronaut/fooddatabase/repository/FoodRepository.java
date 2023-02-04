package me.agronaut.fooddatabase.repository;

import me.agronaut.fooddatabase.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodRepository extends MongoRepository<Food, String> {
    void deleteAllByBarcode(String barcode);
}
