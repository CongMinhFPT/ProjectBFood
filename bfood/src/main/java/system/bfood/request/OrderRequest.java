package system.bfood.request;

import lombok.Data;
import system.bfood.model.Address;

@Data
public class OrderRequest {
	
	private Long restaurantId;
	
	private Address deliveryAddress;
}
