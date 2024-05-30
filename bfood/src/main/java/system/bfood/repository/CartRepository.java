package system.bfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import system.bfood.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
