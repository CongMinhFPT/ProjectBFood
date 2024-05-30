package system.bfood.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import system.bfood.model.Food;
import system.bfood.model.Restaurant;
import system.bfood.model.User;
import system.bfood.request.CreateFoodRequest;
import system.bfood.response.MessageRespone;
import system.bfood.service.FoodService;
import system.bfood.service.RestaurantService;
import system.bfood.service.UserService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

	@Autowired
	private FoodService foodService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@PostMapping
	public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req, 
			@RequestHeader ("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
		Food food = foodService.createFood(req, req.getCategory(), restaurant);
		
		return new ResponseEntity<>(food, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageRespone> deleteFood(@PathVariable Long id, 
			@RequestHeader ("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		foodService.deleteFood(id);
		MessageRespone res = new MessageRespone();
		res.setMessage("Food deleted successfully");
		
		
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Food> updateFoodAvaibilityStatus(@PathVariable Long id, 
			@RequestHeader ("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		foodService.updateAvailibityStatus(id);
		Food food = foodService.updateAvailibityStatus(id);
		return new ResponseEntity<>(food, HttpStatus.CREATED);
	}
}
