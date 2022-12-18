package me.agronaut.foodDatabase.controller;

import jakarta.validation.Valid;
import me.agronaut.foodDatabase.model.FoodDto;
import me.agronaut.foodDatabase.service.FoodService;
import me.agronaut.foodDatabase.service.OpenFoodFactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderion.model.ProductResponse;


@RestController
public class FoodController {
    @Autowired
    FoodService foodSD;
    @Autowired
    OpenFoodFactsService pffSD;

    @PostMapping("/food")
    public ResponseEntity<FoodDto> addFood(@Valid @RequestBody FoodDto food) {
        return new ResponseEntity<FoodDto>(foodSD.save(food), HttpStatus.CREATED);
    }

    @GetMapping("/get-food/{code}")
    public ResponseEntity<FoodDto> getFood(@PathVariable String code) {
        return new ResponseEntity<FoodDto>(pffSD.getByCode(code), HttpStatus.FOUND);
    }
}
