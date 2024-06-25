export const isPresentInFavorites = (favorites, restaurant) => {
  if (!restaurant || !restaurant.id) {
    console.error("Restaurant is undefined or missing 'id' property");
    return false;
  }
  for (let item of favorites) {
    if (restaurant.id === item.id) {
      return true;
    }
  }
  return false;
};
