package me.agronaut.foodDatabase.service;

import me.agronaut.foodDatabase.model.Food;
import me.agronaut.foodDatabase.model.FoodDto;
import me.agronaut.foodDatabase.repository.FoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Page<FoodDto> getAllFood(Pageable pageable) {
        List<Food> res = foodRepository.findAll();
        return new PageImpl<>(res.stream().map(iter -> modelMapper.map(iter, FoodDto.class)).toList(), pageable, res.size());
    }
}
