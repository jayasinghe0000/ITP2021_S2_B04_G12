package lk.sliit.hotel.dao.restaurantDAO.onlineOrderDAO;

import lk.sliit.hotelManagement.entity.restaurant.onlineOrder.RestaurantOnlineOrder;
import lk.sliit.hotelManagement.entity.restaurant.onlineOrder.RestaurantOnlineOrderDetails;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantOnlineOrderDetailsDAO extends CrudRepository<RestaurantOnlineOrderDetails,Integer> {

    Iterable<RestaurantOnlineOrderDetails> findAllByRestaurantOnlineOrderEquals(RestaurantOnlineOrder id);
}
