package me.agronaut.foodDatabase.service;

import me.agronaut.foodDatabase.model.Food;
import me.agronaut.foodDatabase.model.FoodDto;
import me.agronaut.foodDatabase.repository.FoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {


    private final FoodRepository foodRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FoodService(FoodRepository foodRepository, ModelMapper modelMapper) {
        this.foodRepository = foodRepository;
        this.modelMapper = modelMapper;
    }

    public FoodDto save(FoodDto foodDto) {
        Food foodToSave = modelMapper.map(foodDto, Food.class);
        return modelMapper.map(foodRepository.save(foodToSave), FoodDto.class);
    }
}
