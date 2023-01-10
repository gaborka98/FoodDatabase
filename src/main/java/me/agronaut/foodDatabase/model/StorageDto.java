package me.agronaut.foodDatabase.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@Validated
public class StorageDto {
    private Long totalCount;
    private Integer count;
    private FoodDto food;
}
