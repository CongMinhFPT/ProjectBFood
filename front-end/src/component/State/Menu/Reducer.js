import * as aciotnTypes from "./ActionType";

const initialState = {
  menuItems: [],
  loading: false,
  error: null,
  search: [],
  message: null,
};

const menuItemReducer = (state = initialState, action) => {
  switch (action.type) {
    case aciotnTypes.CREATE_MENU_ITEM_REQUEST:
    case aciotnTypes.GET_MENU_ITEMS_BY_RESTAURANT_ID_REQUEST:
    case aciotnTypes.DELETE_MENU_ITEM_REQUEST:
    case aciotnTypes.SEARCH_MENU_ITEM_REQUEST:
    case aciotnTypes.UPDATE_MENU_ITEMS_AVAILABILITY_REQUEST:
      return {
        ...state,
        loading: true,
        error: null,
        message: null,
      };
    case aciotnTypes.CREATE_MENU_ITEM_SUCCESS:
      return {
        ...state,
        loading: false,
        menuItems: [...state.menuItems, action.payload],
        message: "Food Created Successfully",
      };
    case aciotnTypes.GET_MENU_ITEMS_BY_RESTAURANT_ID_SUCCESS:
      return {
        ...state,
        loading: false,
        menuItems: action.payload,
      };
    case aciotnTypes.DELETE_MENU_ITEM_SUCCESS:
      return {
        ...state,
        loading: false,
        menuItems: state.menuItems.filter(
          (menuItem) => menuItem.id !== action.payload
        ),
      };
    case aciotnTypes.UPDATE_MENU_ITEMS_AVAILABILITY_SUCCESS:
      return {
        ...state,
        loading: false,
        menuItems: state.menuItems.map((menuItem) =>
          menuItem.id === action.payload.id ? action.payload : menuItem
        ),
      };
    case aciotnTypes.SEARCH_MENU_ITEM_SUCCESS:
      return {
        ...state,
        loading: false,
        search: action.payload,
      };
    case aciotnTypes.CREATE_MENU_ITEM_FAILURE:
    case aciotnTypes.GET_MENU_ITEMS_BY_RESTAURANT_ID_FAILURE:
    case aciotnTypes.DELETE_MENU_ITEM_FAILURE:
    case aciotnTypes.SEARCH_MENU_ITEM_FAILURE:
    case aciotnTypes.UPDATE_MENU_ITEMS_AVAILABILITY_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload,
        message: null,
      };
    default:
      return state;
  }
};
export default menuItemReducer;
