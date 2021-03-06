package lk.sliit.hotel.dao.restaurantDAO;

import lk.sliit.hotel.entity.restaurant.OnlineLocation;
import org.springframework.data.repository.CrudRepository;

public interface OnlineCustomerLocations extends CrudRepository<OnlineLocation,Integer> {
    OnlineLocation findTopByOrderByLocationIdDesc();
}
