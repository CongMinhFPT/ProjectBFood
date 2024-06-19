import * as aciotnTypes from "./ActionType";

const initialState = {
  loading: false,
  error: null,
  orders: [],
};

const restaurantsOrderReducer = (state = initialState, action) => {
  switch (action.type) {
    case aciotnTypes.GET_RESTAURANTS_ORDER_REQUEST:
    case aciotnTypes.UPDATE_ORDER_STATUS_REQUEST:
      return { ...state, loading: true, error: null };
    case aciotnTypes.GET_RESTAURANTS_ORDER_SUCCESS:
      return { ...state, loading: false, orders: action.payload };
    case aciotnTypes.UPDATE_ORDER_STATUS_SUUCESS:
      return {
        ...state,
        loading: false,
        orders: state.orders.map((order) =>
          order.id === action.payload.id ? action.payload : order
        ),
      };
    case aciotnTypes.GET_RESTAURANTS_ORDER_FAILURE:
    case aciotnTypes.UPDATE_ORDER_STATUS_FAILURE:
      return { ...state, loading: false, error: action.error };
    default:
      return state;
  }
};

export default restaurantsOrderReducer;
