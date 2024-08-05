package system.bfood.request;

import java.util.List;

import lombok.Data;
import system.bfood.model.Address;
import system.bfood.model.ContactInformation;

@Data
public class CreateRestaurantRequest {

	private Long id;
	private String name;
	private String description;
	private String cuisineType;
	private Address address;
	private ContactInformation contactInformation;
	private String openingHours;
	private List<String> images;
}
