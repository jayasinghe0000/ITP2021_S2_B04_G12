package lk.sliit.hotel.service.custom;

import lk.sliit.hotel.dto.hr.DepartmentDTO;
import lk.sliit.hotel.dto.manager.EmployeeDTO;
import lk.sliit.hotel.service.SuperBO;

import java.util.List;

public interface ManageBO extends SuperBO {
    void save(EmployeeDTO employeeDTO);

    List<EmployeeDTO> findAllUser();

    void deleteEmployee(int userId);

    List<DepartmentDTO> findAllDepartment();


    EmployeeDTO findHighestEmployeeId();

    EmployeeDTO findEmployeeById(int userId);

    DepartmentDTO findHighestDepartmentId();

    DepartmentDTO findDepertmentById(int departmentId);

    void saveDepertment(DepartmentDTO departmentDTO);

    void deleteDepartment(int departmentId);
}
