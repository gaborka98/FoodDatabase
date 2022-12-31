package me.agronaut.foodDatabase.service;

import me.agronaut.foodDatabase.model.OpenFoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderion.model.Product;
import pl.coderion.service.OpenFoodFactsWrapper;

@Service
public class OpenFoodFactsService {

    private final OpenFoodFactsWrapper wrapper;

    @Autowired
    public OpenFoodFactsService(OpenFoodFactsWrapper wrapper){
        this.wrapper = wrapper;
    }

    public OpenFoodDto getByCode(String code) {
        Product response = wrapper.fetchProductByCode(code).getProduct();
        OpenFoodDto res = new OpenFoodDto();
        res.setAllergens(response.getAllergensTags());
        res.setIngredients(response.getIngredientsText());
        res.setName(response.getProductName());
        res.setBarcode(response.getCode());
        res.setQuantity(Integer.parseInt(response.getProductQuantity()));
        return res;
    }
}
