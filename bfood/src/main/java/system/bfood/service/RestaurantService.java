package system.bfood.service;

import java.util.List;

import system.bfood.DTO.RestaurantDTO;
import system.bfood.model.Restaurant;
import system.bfood.model.User;
import system.bfood.request.CreateRestaurantRequest;

public interface RestaurantService {

	public Restaurant createRestaurant(CreateRestaurantRequest req, User user);
	
	public Restaurant updateRestautant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;
	
	public void deleteRestaurant(Long restaurantId) throws Exception;
	
	public List<Restaurant> getAllRestaurant();
	
	public List<Restaurant> searchRestaurant(String keyword);
	
	public Restaurant findRestaurantById(Long id) throws Exception; 
	
	public Restaurant getRestaurantByUserId(Long userId) throws Exception;
	
	public RestaurantDTO addToFavorites(Long restaurantId, User user) throws Exception;
	
	public Restaurant updateRestaurantStatus(Long id) throws Exception;
}
