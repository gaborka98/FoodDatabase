package me.agronaut.foodDatabase;

import me.agronaut.foodDatabase.model.OpenFoodDto;
import me.agronaut.foodDatabase.model.StorageDto;
import me.agronaut.foodDatabase.service.FoodService;
import me.agronaut.foodDatabase.service.OpenFoodFactsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

;

@SpringBootTest
class FoodServiceTest {
    @Autowired private FoodService service;
    @Autowired private OpenFoodFactsService service2;

    @Test
    void testGetAllFood() {
        Page<StorageDto> res = service.getAllFood(PageRequest.of(0,10));

        System.out.println(res.getContent());
        Assertions.assertFalse(res.getContent().isEmpty());
    }

    @Test
    void testGetFoodByBarcode(){
        OpenFoodDto food = service2.getByCode("7622300291785");

        System.out.println(food);

        Assertions.assertNotNull(food);
    }
}
