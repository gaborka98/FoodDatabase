package me.agronaut.fooddatabase;

import me.agronaut.fooddatabase.model.OpenFoodDto;
import me.agronaut.fooddatabase.model.StorageDto;
import me.agronaut.fooddatabase.service.FoodService;
import me.agronaut.fooddatabase.service.OpenFoodFactsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.coderion.service.impl.OpenFoodFactsWrapperImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class FoodServiceTest {
    private static FoodService service;
    private static OpenFoodFactsService service2;

    @BeforeAll
    static void setUp() {
        service = mock(FoodService.class);
        //service2 = mock(OpenFoodFactsService.class);
        service2 = new OpenFoodFactsService(new OpenFoodFactsWrapperImpl());
        when(service.getAllFood(any())).thenReturn(new PageImpl<>(List.of(new StorageDto()), Pageable.ofSize(10), 1));
        //when(service2.getByCode("7622300291785")).thenReturn(OpenFoodDto.builder().barcode("7622300291785").build());
    }

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

        Assertions.assertEquals("7622300291785", food.getBarcode());
        Assertions.assertNotNull(food);
    }
}
