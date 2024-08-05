package system.bfood.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.bfood.model.Category;
import system.bfood.model.Restaurant;
import system.bfood.repository.CategoryRepository;
import system.bfood.service.CategoryService;
import system.bfood.service.RestaurantService;

@Service
public class CategoryServiceImp implements CategoryService{

	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Override
	public Category createCategory(String name, Long userId) throws Exception {
		Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
		Category category = new Category();
		category.setName(name);
		category.setRestaurant(restaurant);
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
		Restaurant restaurnt = restaurantService.findRestaurantById(id);
		return categoryRepository.findByRestaurantId(id);
	}

	@Override
	public Category findCategoryById(Long id) throws Exception {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		
		if(optionalCategory.isEmpty()) {
			throw new Exception("Category not found");
		}
		return optionalCategory.get();
	}

}
