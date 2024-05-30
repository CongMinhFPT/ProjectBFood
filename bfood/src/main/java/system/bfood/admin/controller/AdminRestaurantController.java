package system.bfood.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import system.bfood.model.Restaurant;
import system.bfood.model.User;
import system.bfood.request.CreateRestaurantRequest;
import system.bfood.response.MessageRespone;
import system.bfood.service.RestaurantService;
import system.bfood.service.UserService;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping()
	public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		
		Restaurant restaurant = restaurantService.createRestaurant(req, user);
		return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestaurantRequest req,
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		
		Restaurant restaurant = restaurantService.updateRestautant(id, req);
		return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageRespone> deleteRestaurant(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		
		restaurantService.deleteRestaurant(id);
		
		MessageRespone res = new MessageRespone();
		res.setMessage("Restaurant deleted successfully");
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<Restaurant> updateRestaurantStatus(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		
		Restaurant restaurant = restaurantService.updateRestaurantStatus(id);
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<Restaurant> findRestaurantByUserId(
			@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		
		Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}
}
