package system.bfood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import system.bfood.model.Category;
import system.bfood.model.User;
import system.bfood.service.CategoryService;
import system.bfood.service.UserService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;
	
	@GetMapping("/restaurant")
	public ResponseEntity<List<Category>> getRestaurantCategory(@RequestHeader("Authorzation") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		List<Category> categories = categoryService.findCategoryByRestaurantId(user.getId());
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
}
