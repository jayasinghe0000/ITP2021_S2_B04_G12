package lk.sliit.hotel.dao.beverageDAO;

import lk.sliit.hotelManagement.entity.barManage.BarOrderDetails;
import org.springframework.data.repository.CrudRepository;

public interface BarOrdersDetailsDAO extends CrudRepository<BarOrderDetails,Integer> {
}
