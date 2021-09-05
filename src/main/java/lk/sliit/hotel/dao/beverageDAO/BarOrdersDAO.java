package lk.sliit.hotel.dao.beverageDAO;

import lk.sliit.hotelManagement.entity.barManage.BarOrders;
import org.springframework.data.repository.CrudRepository;

public interface BarOrdersDAO extends CrudRepository<BarOrders,Integer> {
    BarOrders findTopByOrderByOrderIdDesc();

}
