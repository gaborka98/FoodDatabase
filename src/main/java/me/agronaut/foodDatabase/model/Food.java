package me.agronaut.foodDatabase.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.coderion.model.ProductResponse;


@Document("foods")
@Data
public class Food {
    @Id
    private String id;
    private String name;
    private Integer quantity;

    private String[] allergens;
    private String ingredients;

}
