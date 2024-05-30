package system.bfood.service;

import java.util.List;

import system.bfood.model.Category;
import system.bfood.model.Food;
import system.bfood.model.Restaurant;
import system.bfood.request.CreateFoodRequest;

public interface FoodService {

	public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

	void deleteFood(Long foodId) throws Exception;

	public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarain, boolean isNonveg, boolean isSeasonal,
			String foodCategory);
	
	public List<Food> searchFood(String keyword);
	
	public Food findFoodById(Long foodId) throws Exception;
	
	public Food updateAvailibityStatus(Long foodId) throws Exception;
	
}
