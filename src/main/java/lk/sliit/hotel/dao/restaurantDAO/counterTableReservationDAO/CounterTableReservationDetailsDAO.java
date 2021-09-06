package lk.sliit.hotel.dao.restaurantDAO.counterTableReservationDAO;

import lk.sliit.hotel.entity.restaurant.counterTableReservation.CounterTableReservationDetails;

import org.springframework.data.repository.CrudRepository;

public interface CounterTableReservationDetailsDAO extends CrudRepository<CounterTableReservationDetails,Integer> {
}
