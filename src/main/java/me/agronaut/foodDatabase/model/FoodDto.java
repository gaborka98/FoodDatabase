package me.agronaut.fooddatabase.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.validation.annotation.Validated;


@Data
@NoArgsConstructor
@Validated
public class FoodDto {
    private String id;
    @NotNull(message = "Name is required")
    @Size(min = 3, max = 255, message = "Name length must be between 3 and 255")
    private String name;
    private String barcode;
    @NotNull(message = "quantity is required")
    private Integer quantity;

    private String[] allergens;
    private String[] ingredients;
    private Binary photo;
}
