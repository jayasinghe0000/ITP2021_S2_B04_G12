package lk.sliit.hotel.service.custom.impl;

import lk.sliit.hotel.dao.hrDAO.AttendanceDAO;
import lk.sliit.hotel.dao.hrDAO.DepartmentDAO;
import lk.sliit.hotel.dao.manageSystemDAO.EmployeeDAO;
import lk.sliit.hotel.dto.hr.DepartmentDTO;
import lk.sliit.hotel.dto.manager.EmployeeDTO;
import lk.sliit.hotel.entity.hr.Department;
import lk.sliit.hotel.entity.manager.Employee;
import lk.sliit.hotel.service.custom.ManageBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ManageBOImpl implements ManageBO {
    @Autowired
    EmployeeDAO manageDAO;
    @Autowired
    DepartmentDAO departmentDAO;

    @Autowired
    AttendanceDAO attendanceDAO;
    @Override
    public void save(EmployeeDTO employeeDTO) {

        manageDAO.save(new Employee(
                employeeDTO.getUserId(),
                employeeDTO.getName(),
                employeeDTO.getMobileNo(),
                employeeDTO.getEmail(),
                employeeDTO.getAddress(),
                employeeDTO.getPosition(),
                employeeDTO.getPassword(),
                employeeDTO.getDateOfBirth(),
                employeeDTO.getGender(),
                employeeDTO.getSalary(),
                employeeDTO.getDate(),
                employeeDTO.getImage(),
                departmentDAO.findOne(employeeDTO.getDepartment())
        ));
    }

    @Override
    public List<EmployeeDTO> findAllUser() {
        Iterable<Employee> all = manageDAO.findAll();

        List<EmployeeDTO> dtos = new ArrayList<>();
        for (Employee employee: all) {
            dtos.add(new EmployeeDTO(
                    employee.getUserId(),
                    employee.getName(),
                    employee.getMobileNo(),
                    employee.getEmail(),
                    employee.getAddress(),
                    employee.getPosition(),
                    employee.getPassword(),
                    employee.getDateOfBirth(),
                    employee.getGender(),
                    employee.getSalary(),
                    employee.getDate(),
                    employee.getImage(),
                    employee.getDepartment().getDepartmentId()
            ));
        }
        return dtos;

    }

    @Override
    public void deleteEmployee(int userId) {
        manageDAO.delete(userId);
    }

    @Override
    public List<DepartmentDTO> findAllDepartment() {
        Iterable<Department> all = departmentDAO.findAll();
        List<DepartmentDTO> dtos = new ArrayList<>();
        for (Department department: all) {
            dtos.add(new DepartmentDTO(
                   department.getDepartmentId(),
                    department.getDepartmentName()
            ));
        }
        return dtos;
    }

    @Override
    public EmployeeDTO findHighestEmployeeId() {
        Employee lastItem = null;
        try {
            lastItem = manageDAO.findTopByOrderByUserIdDesc();
        } catch (Exception e){

        }

        return new EmployeeDTO(lastItem.getUserId());
    }

    @Override
    public EmployeeDTO findEmployeeById(int userId) {
        Employee employee = manageDAO.findOne(userId);
        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getUserId(),
                employee.getName(),
                employee.getMobileNo(),
                employee.getEmail(),
                employee.getAddress(),
                employee.getPosition(),
                employee.getPassword(),
                employee.getDateOfBirth(),
                employee.getGender(),
                employee.getSalary(),
                employee.getDate(),
                employee.getImage(),
                employee.getDepartment().getDepartmentId()
        );
        return employeeDTO;
    }

    @Override
    public DepartmentDTO findHighestDepartmentId() {
        Department lastItem = null;
        try {
            lastItem = departmentDAO.findTopByOrderByDepartmentIdDesc();
        } catch (Exception e){

        }
        return new DepartmentDTO(lastItem.getDepartmentId());
    }

    @Override
    public DepartmentDTO findDepertmentById(int departmentId) {
        Department department = departmentDAO.findOne(departmentId);
        DepartmentDTO departmentDTO = new DepartmentDTO(
                department.getDepartmentId(),
                department.getDepartmentName()
        );
        return departmentDTO;
    }


    @Override
    public void saveDepertment(DepartmentDTO departmentDTO) {
        departmentDAO.save(new Department(
                departmentDTO.getDepartmentId(),
                departmentDTO.getDepartmentName()

        ));
    }

    @Override
    public void deleteDepartment(int departmentId) {
        departmentDAO.delete(departmentId);
    }
}
