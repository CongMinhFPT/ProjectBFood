package system.bfood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import system.bfood.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	public List<Order> findByCustomerId(Long userId);
	
	public List<Order> findByRestaurantId(Long restaurantId);
}
