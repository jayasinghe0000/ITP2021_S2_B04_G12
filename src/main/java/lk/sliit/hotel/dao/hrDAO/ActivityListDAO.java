package lk.sliit.hotel.dao.hrDAO;

import lk.sliit.hotel.entity.hr.ActivityList;
import org.springframework.data.repository.CrudRepository;

public interface ActivityListDAO extends CrudRepository<ActivityList,Integer> {

    ActivityList findTopByOrderByActivityIdDesc();
}
