package lk.sliit.hotel.dao.restaurantDAO;

import lk.sliit.hotelManagement.entity.restaurant.RestaurantBill;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantBillDAO extends CrudRepository<RestaurantBill,Integer> {
}
