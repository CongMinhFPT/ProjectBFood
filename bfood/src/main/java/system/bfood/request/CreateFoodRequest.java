package system.bfood.request;

import java.util.List;

import lombok.Data;
import system.bfood.model.Category;
import system.bfood.model.IngredientsItem;

@Data
public class CreateFoodRequest {

	private String name;
	private String description;
	private Long price;
	private Category category;
	private List<String> image;
	private Long restaurantId;
	private boolean vegetarin;
	private boolean seasional;
	private List<IngredientsItem> ingredientsItems;
}
