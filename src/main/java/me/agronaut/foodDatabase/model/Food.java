package me.agronaut.fooddatabase.model;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document("foods")
@Data
public class Food {
    @Id
    private String id;
    @Field("name")
    private String name;
    @Field("barcode")
    private String barcode;
    @Field("quantity")
    private Integer quantity;

    @Field("allergens")
    private String[] allergens;
    @Field("ingredients")
    private String[] ingredients;
    @Field("image")
    private Binary image;

}
