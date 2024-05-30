package system.bfood.admin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import system.bfood.model.Category;
import system.bfood.model.User;
import system.bfood.service.CategoryService;
import system.bfood.service.UserService;

@RestController
@RequestMapping("/api/admin/category")
public class AdminCategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestBody Category category, @RequestHeader("Authorzation") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		Category createdCategory = categoryService.createCategory(category.getName(), user.getId());
		return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
	}
	
}
