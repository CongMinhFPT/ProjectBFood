package system.bfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import system.bfood.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}