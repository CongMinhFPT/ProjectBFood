package system.bfood.serviceImp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.bfood.model.Category;
import system.bfood.model.Food;
import system.bfood.model.Restaurant;
import system.bfood.repository.FoodRepository;
import system.bfood.request.CreateFoodRequest;
import system.bfood.service.FoodService;

@Service
public class FoodServiceImp implements FoodService {
	
	@Autowired
	private FoodRepository foodRepository;

	@Override
	public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
		Food food = new Food();
		
		food.setFoodCategory(category);
		food.setRestaurant(restaurant);
		food.setDescription(req.getDescription());
		food.setImage(req.getImage());
		food.setName(req.getName());
		food.setPrice(req.getPrice());
		food.setIngredients(req.getIngredientsItems());
		food.setSeasonal(req.isSeasional());
		food.setVegetarian(req.isVegetarin());
		
		Food saveFood = foodRepository.save(food);
		restaurant.getFoods().add(saveFood);
		return saveFood;
	}

	@Override
	public void deleteFood(Long foodId) throws Exception {
		Food food = findFoodById(foodId);
		food.setRestaurant(null);
		foodRepository.save(food);
		
	}

	@Override
	public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarain, boolean isNonveg, boolean isSeasonal,
			String foodCategory) {
		List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
		
		if(isVegitarain) {
			foods = filterByVegetarian(foods, isVegitarain);
		}
		if(isNonveg) {
			foods = filterByNonveg(foods, isNonveg);
		}
		if(isSeasonal) {
			foods = filterBySeasonal(foods, isSeasonal);
		}
		if(foodCategory != null && foodCategory.equals("")) {
			foods = filterByCategory(foods, foodCategory);
		}
		return foods;
	}

	private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
		return foods.stream().filter(food ->{
			if(food.getFoodCategory() != null){
				return food.getFoodCategory().getName().equals(foodCategory);
			}
			return false;
		}).collect(Collectors.toList());
	}

	private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
		return foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());

	}

	private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {
		return foods.stream().filter(food -> food.isVegetarian()==false).collect(Collectors.toList());

	}

	private List<Food> filterByVegetarian(List<Food> foods, boolean isVegitarain) {
		return foods.stream().filter(food -> food.isVegetarian()==isVegitarain).collect(Collectors.toList());
	}

	@Override
	public List<Food> searchFood(String keyword) {
		return foodRepository.searchFood(keyword);
	}

	@Override
	public Food findFoodById(Long foodId) throws Exception {
		Optional<Food> optionFood = foodRepository.findById(foodId);
		
		if(optionFood.isEmpty()) {
			throw new Exception("Food not exit...");
		}
		
		return optionFood.get();
	}

	@Override
	public Food updateAvailibityStatus(Long foodId) throws Exception {
		Food food = findFoodById(foodId);
		
		food.setAvaitable(!food.isAvaitable());
		return foodRepository.save(food);
	}

}
