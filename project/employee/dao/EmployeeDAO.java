package project.employee.dao;

import project.employee.connector.ConnectorFactory;
import project.employee.dto.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements IEmployeeDAO{

    @Override
    public List getEmployee() {
        ArrayList<Employee> empList = null;
        try {
            Connection con = ConnectorFactory.requestConnection();
            String query = "select * from emp";
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            empList = new ArrayList<Employee>();

            while (res.next()){
                int id = res.getInt(1);
                String name = res.getString(2);
                String designation = res.getString(3);
                int salary = res.getInt(4);

                Employee e = new Employee(id,name,designation,salary);
                empList.add(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return empList;
    }

    @Override
    public Employee getEmployee(int id) {
        Employee e = null;
        try {
            Connection con =  ConnectorFactory.requestConnection();
            String query = "select * from emp where id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1,id);
            ResultSet res = pstmt.executeQuery();
            res.next();
             e = new Employee(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return e;
    }

    @Override
    public boolean insertEmployee(int id, String name, String designation, int salary) {
        int isInserted = 0;
        try {
            Connection con = ConnectorFactory.requestConnection();
            String query = "insert into emp (`id`,`name`,`designation`,`salary`) values (?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1,id);
            pstmt.setString(2,name);
            pstmt.setString(3,designation);
            pstmt.setInt(4,salary);
            isInserted = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return isInserted == 1 ? true : false;
    }

    @Override
    public boolean updateEmployee(Employee e) {
        int isUpdated = 0;
        try{
            Connection con = ConnectorFactory.requestConnection();
            String query = "update emp set name = ?, designation = ?, salary = ? where id = ?";
            PreparedStatement pstmt =  con.prepareStatement(query);
            pstmt.setString(1,e.getName());
            pstmt.setString(2,e.getDesignation());
            pstmt.setInt(3,e.getSalary());
            pstmt.setInt(4,e.getId());

            isUpdated = pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return isUpdated == 1;
    }

    @Override
    public boolean deleteEmployee(int id) {
        int isDeleted = 0;
        try {
            Connection con = ConnectorFactory.requestConnection();
            String query = "delete from emp where id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1,id);
            isDeleted = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return isDeleted == 1;
    }
}
