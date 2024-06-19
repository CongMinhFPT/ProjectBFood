import { api } from "../../config/api";
import {
  GET_RESTAURANTS_ORDER_FAILURE,
  GET_RESTAURANTS_ORDER_REQUEST,
  GET_RESTAURANTS_ORDER_SUCCESS,
  UPDATE_ORDER_STATUS_FAILURE,
  UPDATE_ORDER_STATUS_REQUEST,
  UPDATE_ORDER_STATUS_SUUCESS,
} from "./ActionType";

export const updateOrderStatus = ({ orderId, orderStatus, jwt }) => {
  return async (dispatch) => {
    try {
      dispatch({ type: UPDATE_ORDER_STATUS_REQUEST });

      const response = await api.put(
        `/api/admin/orders/${orderId}/${orderStatus}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );
      console.log("update order", response.data);
      dispatch({ type: UPDATE_ORDER_STATUS_SUUCESS, payload: response.data });
    } catch (error) {
      console.log("error", error);
      dispatch({ type: UPDATE_ORDER_STATUS_FAILURE, error });
    }
  };
};

export const fetchRestaurantsOrder = ({ restaurantId, orderStatus, jwt }) => {
  return async (dispatch) => {
    try {
      dispatch({ type: GET_RESTAURANTS_ORDER_REQUEST });

      const { data } = await api.get(
        `/api/admin/orders/restaurant/${restaurantId}`,
        { params: { order_status: orderStatus } },
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );
      console.log("restaurants order...", data);
      dispatch({ type: GET_RESTAURANTS_ORDER_SUCCESS, payload: data });
    } catch (error) {
      console.log("error", error);
      dispatch({ type: GET_RESTAURANTS_ORDER_FAILURE, error });
    }
  };
};
