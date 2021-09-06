package lk.sliit.hotel.dao.restaurantDAO;

import lk.sliit.hotel.entity.restaurant.RestaurantBill;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantBillDAO extends CrudRepository<RestaurantBill,Integer> {
}
