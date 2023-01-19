package me.agronaut.foodDatabase.service;

import me.agronaut.foodDatabase.exception.ApiError;
import me.agronaut.foodDatabase.model.OpenFoodDto;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.coderion.model.Product;
import pl.coderion.service.OpenFoodFactsWrapper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;

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

    private Binary downloadImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            Image image = ImageIO.read(url);
        } catch (IOException e) {
            throw 
        }
    }
}
