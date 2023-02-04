package me.agronaut.fooddatabase.service;

import me.agronaut.fooddatabase.model.Food;
import me.agronaut.fooddatabase.model.FoodDto;
import me.agronaut.fooddatabase.model.StorageDto;
import me.agronaut.fooddatabase.repository.FoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private final ModelMapper modelMapper;
    private final MongoTemplate mt;

    @Autowired
    public FoodService(FoodRepository foodRepository, ModelMapper modelMapper, MongoTemplate mt) {
        this.foodRepository = foodRepository;
        this.modelMapper = modelMapper;
        this.mt = mt;
    }

    public FoodDto save(FoodDto foodDto) {
        Food foodToSave = modelMapper.map(foodDto, Food.class);
        return modelMapper.map(foodRepository.save(foodToSave), FoodDto.class);
    }

    public Page<StorageDto> getAllFood(Pageable pageable) {
        Aggregation agg = Aggregation.newAggregation(
                project("totalCount", "barcode", "quantity", "name", "allergens", "ingredients"),
                group("barcode")
                        .sum("quantity").as("totalCount")
                        .count().as("count")
                        .first(ROOT).as("food")
        );
        AggregationResults<StorageDto> res = mt.aggregate(agg, "foods", StorageDto.class);

        return new PageImpl<>(res.getMappedResults(), pageable, res.getMappedResults().size());
    }

    public void deleteFood(String id) {
        foodRepository.deleteById(id);
    }
}
