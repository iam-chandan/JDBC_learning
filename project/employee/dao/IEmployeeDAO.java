package project.employee.dao;

import project.employee.dto.Employee;

import java.util.List;

public interface IEmployeeDAO {
    List getEmployee();
    Employee getEmployee(int id);
    boolean insertEmployee(int id,String name,String designation,int salary);
    boolean updateEmployee(Employee e);
    boolean deleteEmployee(int id);
}
