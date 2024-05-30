package system.bfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import system.bfood.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmail(String username);
}
