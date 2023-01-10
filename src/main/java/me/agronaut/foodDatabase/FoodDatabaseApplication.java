package me.agronaut.foodDatabase;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import pl.coderion.service.OpenFoodFactsWrapper;
import pl.coderion.service.impl.OpenFoodFactsWrapperImpl;

@SpringBootApplication
@EnableMongoRepositories
public class FoodDatabaseApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	public OpenFoodFactsWrapper openFoodFactsWrapper() {
		return new OpenFoodFactsWrapperImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(FoodDatabaseApplication.class, args);
	}

}
