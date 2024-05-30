package system.bfood.serviceImp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.bfood.DTO.RestaurantDTO;
import system.bfood.model.Address;
import system.bfood.model.Restaurant;
import system.bfood.model.User;
import system.bfood.repository.AddressRepository;
import system.bfood.repository.RestaurantRepository;
import system.bfood.repository.UserRepository;
import system.bfood.request.CreateRestaurantRequest;
import system.bfood.service.RestaurantService;

@Service
public class RestaurantServiceImp implements RestaurantService{

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
		Address address = addressRepository.save(req.getAddress());
		
		Restaurant restaurant = new Restaurant();
		restaurant.setAddress(address);
		restaurant.setContactInformation(req.getContactInformation());
		restaurant.setCuisineType(req.getCuisineType());
		restaurant.setDescription(req.getDescription());
		restaurant.setImages(req.getImages());
		restaurant.setName(req.getName());
		restaurant.setOpeningHours(req.getOpningHours());
		restaurant.setRegistrationDate(LocalDateTime.now());
		restaurant.setOwner(user);
		
		return restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant updateRestautant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);
		
		if(restaurant.getCuisineType() != null) {
			restaurant.setCuisineType(updatedRestaurant.getCuisineType());
		}
		if(restaurant.getDescription() != null) {
			restaurant.setDescription(updatedRestaurant.getDescription());
		}
		if(restaurant.getName() != null) {
			restaurant.setName(updatedRestaurant.getName());
		}
		return restaurantRepository.save(restaurant);
	}

	@Override
	public void deleteRestaurant(Long restaurantId) throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);
		
		restaurantRepository.delete(restaurant);
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurant> searchRestaurant(String keyword) {
		return restaurantRepository.findBySearchQuery(keyword);
	}

	@Override
	public Restaurant findRestaurantById(Long id) throws Exception {
		Optional<Restaurant> opt = restaurantRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new Exception("Restaurant not found with id: " + id);
		}
		return opt.get();
	}

	@Override
	public Restaurant getRestaurantByUserId(Long userId) throws Exception {
		Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
		
		if(restaurant == null) {
			throw new Exception("Restaurant not found with owner id: " +userId);
		}
		return restaurant;
	}

	@Override
	public RestaurantDTO addToFavorites(Long restaurantId, User user) throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);
		
		RestaurantDTO dto = new RestaurantDTO();
		dto.setDescription(restaurant.getDescription());
		dto.setImage(restaurant.getImages());
		dto.setTitle(restaurant.getName());
		dto.setId(restaurantId);
		
		boolean isFavorited = false;
		List<RestaurantDTO> favorites = user.getFavorites();
		for(RestaurantDTO favorite : favorites) {
			if(favorite.getId().equals(restaurantId)) {
				isFavorited = true;
				break;
			}
		}
		
		if(isFavorited) {
			favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
		} else {
			favorites.add(dto);
		}
		
		userRepository.save(user);
		
		return dto;
	}

	@Override
	public Restaurant updateRestaurantStatus(Long id) throws Exception {
		Restaurant restaurant = findRestaurantById(id);
		
		restaurant.setOpen(!restaurant.isOpen());
		return restaurantRepository.save(restaurant);
	}

}