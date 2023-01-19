package me.agronaut.foodDatabase.controller;

import jakarta.validation.Valid;
import me.agronaut.foodDatabase.model.FoodDto;
import me.agronaut.foodDatabase.model.OpenFoodDto;
import me.agronaut.foodDatabase.model.StorageDto;
import me.agronaut.foodDatabase.service.FoodService;
import me.agronaut.foodDatabase.service.OpenFoodFactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class FoodController {
    @Autowired
    FoodService foodSD;
    @Autowired
    OpenFoodFactsService pffSD;

    @PostMapping("/add-food")
    public ResponseEntity<FoodDto> addFood(@Valid @RequestBody FoodDto food) {
        return new ResponseEntity<>(foodSD.save(food), HttpStatus.CREATED);
    }

    @GetMapping("/get-food/{code}")
    public ResponseEntity<OpenFoodDto> getFood(@PathVariable String code) {
        return new ResponseEntity<>(pffSD.getByCode(code), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public Page<StorageDto> getAllByUser(Pageable pageable) {
        return foodSD.getAllFood(pageable);
    }
}