package me.agronaut.fooddatabase;

import me.agronaut.fooddatabase.model.Food;
import me.agronaut.fooddatabase.model.FoodDto;
import me.agronaut.fooddatabase.model.StorageDto;
import me.agronaut.fooddatabase.repository.FoodRepository;
import me.agronaut.fooddatabase.service.FoodService;
import me.agronaut.fooddatabase.service.OpenFoodFactsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.coderion.model.Product;
import pl.coderion.model.ProductResponse;
import pl.coderion.service.OpenFoodFactsWrapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class FoodServiceTest {
    @Autowired
    private FoodService service;
    @Autowired
    private OpenFoodFactsService service2;

    @MockBean
    private OpenFoodFactsWrapper wrapper;
    @MockBean
    private FoodRepository repository;

    @Test
    void testDeleteById() {
        doNothing().when(repository).deleteById(anyString());

        service.deleteFood("mockingId");

        verify(repository, times(1)).deleteById(anyString());
    }

    @Test
    void testDeleteAll() {
        doNothing().when(repository).deleteAllByBarcode(anyString());

        service.deleteAll("blankId");

        verify(repository, times(1)).deleteAllByBarcode(anyString());

    }

    @Test
    void testSave() {
        when(repository.save(any())).thenReturn(Food.builder().id("asd123").name("test").quantity(50).build());

        FoodDto food = service.save(new FoodDto());

        Assertions.assertNotNull(food);
        verify(repository, times(1)).save(any());

    }

    @Test
    void testGetAllFood() {
        Page<StorageDto> res = service.getAllFood(PageRequest.of(0,10));

        System.out.println(res.getContent());
        Assertions.assertFalse(res.getContent().isEmpty());
    }

    @Test
    void testGetFoodByBarcode(){
        ProductResponse fakeProductResponse = new ProductResponse();
        Product fakeProduct = new Product();
        fakeProduct.setCode("7622300291785");
        fakeProduct.setProductName("Cola");
        fakeProduct.setAllergens("tej, cukor, so");
        fakeProduct.setIngredientsText("kakao, vaj, teszta (25%)");
        fakeProduct.setProductQuantity("200");
        fakeProduct.setImageThumbUrl("https://napiszar.hu/m-post-img/2023/02/23/thumb-580x0-d9db6b8a7898dd4798013d58d76be63d.jpg");
        fakeProductResponse.setProduct(fakeProduct);

        when(wrapper.fetchProductByCode(any())).thenReturn(fakeProductResponse);

        FoodDto food = service2.getByCode("7622300291785");

        Assertions.assertEquals("7622300291785", food.getBarcode());
        Assertions.assertNotNull(food);

        fakeProductResponse = new ProductResponse();
        fakeProduct = new Product();
        fakeProduct.setCode("7622300291785");
        fakeProduct.setProductName("Cola");
        fakeProduct.setAllergens("tej, cukor, so");
        fakeProduct.setIngredientsText("kakao, vaj, teszta (25%)");
        fakeProduct.setProductQuantity("200");
        fakeProductResponse.setProduct(fakeProduct);

        when(wrapper.fetchProductByCode(any())).thenReturn(fakeProductResponse);

        food = service2.getByCode("7622300291785");

        Assertions.assertEquals("7622300291785", food.getBarcode());
        Assertions.assertNotNull(food);


        when(wrapper.fetchProductByCode(any())).thenReturn(null);

        food = service2.getByCode("7622300291785");

        Assertions.assertNull(food);
    }
}
