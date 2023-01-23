package me.agronaut.fooddatabase.controller;

import jakarta.validation.Valid;
import me.agronaut.fooddatabase.model.FoodDto;
import me.agronaut.fooddatabase.model.OpenFoodDto;
import me.agronaut.fooddatabase.model.StorageDto;
import me.agronaut.fooddatabase.service.FoodService;
import me.agronaut.fooddatabase.service.OpenFoodFactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController("/")
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
    public Page<StorageDto> getAllByUser(@RequestParam(required = false)Pageable pageable) {
        return foodSD.getAllFood(Objects.requireNonNullElseGet(pageable, () -> PageRequest.of(0, 10)));
    }
}
