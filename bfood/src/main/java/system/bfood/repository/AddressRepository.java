package system.bfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import system.bfood.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
