package me.agronaut.foodDatabase.service;

import me.agronaut.foodDatabase.model.Food;
import me.agronaut.foodDatabase.model.FoodDto;
import me.agronaut.foodDatabase.repository.FoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderion.model.Product;
import pl.coderion.model.ProductResponse;
import pl.coderion.service.OpenFoodFactsWrapper;
import pl.coderion.service.impl.OpenFoodFactsWrapperImpl;

@Service
public class OpenFoodFactsService {

    private final OpenFoodFactsWrapper wrapper = new OpenFoodFactsWrapperImpl();
    private final ModelMapper modelMapper;

    @Autowired
    public OpenFoodFactsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FoodDto getByCode(String code) {
        Product response = wrapper.fetchProductByCode(code).getProduct();
        Food res = new Food();
        res.setAllergens(response.getAllergensTags());
        res.setIngredients(response.getIngredientsText());
        res.setName(response.getProductName());
        res.setQuantity(Integer.parseInt(response.getProductQuantity()));
        return modelMapper.map(res, FoodDto.class);
    }
}
