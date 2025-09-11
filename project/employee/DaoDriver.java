package project.employee;

import project.employee.dao.EmployeeDAO;
import project.employee.dto.Employee;

import java.util.List;
import java.util.Scanner;

public class DaoDriver {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        EmployeeDAO employeeDAO = new EmployeeDAO();


        //get data
        List<Employee> employee = employeeDAO.getEmployee();

        for (Employee e : employee){
            System.out.println(e);
        }



        //get data using id
        Employee e = employeeDAO.getEmployee(scn.nextInt());
        System.out.println(e);



        //insert employee data into databse
        System.out.println("Enter id :");
        int id = scn.nextInt();
        System.out.println("Enter name :");
        String name = scn.next();
        System.out.println("Enter designation :");
        String designation = scn.next();
        System.out.println("Enter salary :");
        int salary = scn.nextInt();

        boolean isInserted = employeeDAO.insertEmployee(id,name,designation,salary);
        System.out.println("is data inserted : " + isInserted);



        //update employee data
        Employee employee = new Employee(5,"Priyanka","SME",36000);
        boolean isUpdated = employeeDAO.updateEmployee(employee);
        System.out.println("is employee updated : " + isUpdated);


        //delete employee data
        boolean isDeleted = employeeDAO.deleteEmployee(8);
        System.out.println("is data deleted from database : " + isDeleted);
    }
}
