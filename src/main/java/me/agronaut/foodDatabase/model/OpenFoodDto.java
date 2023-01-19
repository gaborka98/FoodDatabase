package me.agronaut.foodDatabase.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@Validated
public class OpenFoodDto {
    String barcode;
    String name;
    String ingredients;
    Integer quantity;
    String[] allergens;
    Binary photo;
}
