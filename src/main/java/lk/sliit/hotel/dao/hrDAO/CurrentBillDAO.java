package lk.sliit.hotel.dao.hrDAO;


import lk.sliit.hotel.entity.hr.CurrentBill;
import org.springframework.data.repository.CrudRepository;


public interface CurrentBillDAO extends CrudRepository<CurrentBill,Integer> {
    CurrentBill findTopByOrderByBillIdDesc();

}
