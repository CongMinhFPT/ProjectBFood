package system.bfood.service;

import java.util.List;

import system.bfood.model.IngredientCategory;
import system.bfood.model.IngredientsItem;

public interface IngredientsService {

	public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;
	
	public IngredientCategory findIngredientCategoryById(Long id) throws Exception;
	
	public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;
	
	public IngredientsItem createIngredientsItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception;
	
	public List<IngredientsItem> findRestaurantIngredients(Long restaurantId);
	
	public IngredientsItem updateStock(Long id) throws Exception;
}
