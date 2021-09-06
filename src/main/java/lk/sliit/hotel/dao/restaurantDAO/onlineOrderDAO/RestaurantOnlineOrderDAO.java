package lk.sliit.hotel.dao.restaurantDAO.onlineOrderDAO;

import lk.sliit.hotel.entity.restaurant.onlineOrder.RestaurantOnlineOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface RestaurantOnlineOrderDAO extends CrudRepository<RestaurantOnlineOrder,Integer> {
    RestaurantOnlineOrder findTopByOrderByOrderIdDesc();

    Iterable<RestaurantOnlineOrder> findAllByDateBetween(Date dt, Date todaydate);

    Iterable<RestaurantOnlineOrder> findAllByDateEquals(Date date);

    Iterable<RestaurantOnlineOrder> findAllByOrderStateEquals(String state);
}
