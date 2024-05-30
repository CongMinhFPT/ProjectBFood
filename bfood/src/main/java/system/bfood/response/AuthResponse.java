package system.bfood.response;

import lombok.Data;
import system.bfood.model.USER_ROLE;

@Data
public class AuthResponse {
	
	private String jwt;
	
	private String message;
	
	private USER_ROLE role;
}
