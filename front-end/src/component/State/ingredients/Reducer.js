import * as aciotnTypes from "./ActionType";

const initialState = {
  ingredients: [],
  update: null,
  category: [],
};

export const ingredientReducer = (state = initialState, action) => {
  switch (action.type) {
    case aciotnTypes.GET_INGREDIENTS:
      return {
        ...state,
        ingredients: action.payload,
      };
    case aciotnTypes.GET_INGREDIENT_CATEGORY_SUCCESS:
      return {
        ...state,
        category: action.payload,
      };
    case aciotnTypes.CREATE_INGREDIENT_CATEGORY_SUCCESS:
      return {
        ...state,
        category: [...state.category, action.payload],
      };
    case aciotnTypes.CREATE_INGREDIENT_SUCCESS:
      return {
        ...state,
        ingredients: [...state.ingredients, action.payload],
      };
    case aciotnTypes.UPDATE_STOCK:
      return {
        ...state,
        update: action.payload,
        ingredients: state.ingredients.map((item) =>
          item.id === action.payload.id ? action.payload : item
        ),
      };
    default:
      state;
  }
};

export default ingredientReducer;
