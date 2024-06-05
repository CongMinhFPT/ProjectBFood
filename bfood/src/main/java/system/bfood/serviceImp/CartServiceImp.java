package system.bfood.serviceImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import system.bfood.model.Cart;
import system.bfood.model.CartItem;
import system.bfood.model.Food;
import system.bfood.model.User;
import system.bfood.repository.CartItemRepository;
import system.bfood.repository.CartRepository;
import system.bfood.request.AddCartItemRequest;
import system.bfood.service.CartService;
import system.bfood.service.FoodService;
import system.bfood.service.UserService;

public class CartServiceImp implements CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private FoodService foodService;

	@Override
	public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		Food food = foodService.findFoodById(req.getFoodId());
		Cart cart = cartRepository.findByCustomerId(user.getId());
		
		for(CartItem cartItem : cart.getItem()) {
			if(cartItem.getFood().equals(food)) {
				int newQuantity = cartItem.getQuantity() + req.getQuantity();
				return updateCartItemQuantity(cartItem.getId(), newQuantity);
			}
		}
		CartItem newCartItem = new CartItem();
		newCartItem.setFood(food);
		newCartItem.setCart(cart);
		newCartItem.setQuantity(req.getQuantity());
		newCartItem.setIngredients(req.getIngredients());
		newCartItem.setTotalPrice(req.getQuantity()+food.getPrice());
		
		CartItem savedCartItem = cartItemRepository.save(newCartItem);
		cart.getItem().add(savedCartItem);
		return savedCartItem;
	}

	@Override
	public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
		Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
		if(cartItemOptional.isEmpty()) {
			throw new Exception("CartItem not found...");
		}
		CartItem item = new CartItem();
		item.setQuantity(quantity);
		item.setTotalPrice(item.getFood().getPrice()*quantity);
		return cartItemRepository.save(item);
	}

	@Override
	public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		Cart cart = cartRepository.findByCustomerId(user.getId());
		Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
		if(cartItemOptional.isEmpty()) {
			throw new Exception("CartItem not found...");
		}
		CartItem item = cartItemOptional.get();
		cart.getItem().remove(item);
		return cartRepository.save(cart);
	}

	@Override
	public Long calculateCartTotals(Cart cart) throws Exception {
		Long total = 0L;
		
		for(CartItem cartItem : cart.getItem()) {
			total += cartItem.getFood().getPrice()*cartItem.getQuantity();
		}
		return total;
	}

	@Override
	public Cart findCartById(Long id) throws Exception {
		Optional<Cart> optionalCart = cartRepository.findById(id);
		if(optionalCart.isEmpty()) {
			throw new Exception("Cart not found with id " + id);
		}
		return optionalCart.get();
	}

	@Override
	public Cart findCartByUserId(Long userId) throws Exception {
		Cart cart = cartRepository.findByCustomerId(userId);
		cart.setTotal(calculateCartTotals(cart));
		return cart;
	}

	@Override
	public Cart clearCart(Long userId) throws Exception {
		Cart cart = findCartByUserId(userId);
		cart.getItem().clear();
		return cartRepository.save(cart);
	}

}