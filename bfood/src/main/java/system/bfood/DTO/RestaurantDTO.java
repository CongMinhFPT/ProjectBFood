package system.bfood.DTO;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class RestaurantDTO {

	private String title;
	
	@Column(length = 1000)
	private List<String> image;
	
	private String description;
	private Long id;
}
