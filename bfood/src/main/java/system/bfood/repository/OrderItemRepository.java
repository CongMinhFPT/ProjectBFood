package system.bfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import system.bfood.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
