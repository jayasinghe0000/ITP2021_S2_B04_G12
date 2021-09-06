package lk.sliit.hotel.dao.hrDAO;


import lk.sliit.hotel.entity.hr.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentDAO extends CrudRepository<Department,Integer> {
    Department findTopByOrderByDepartmentIdDesc();
}
