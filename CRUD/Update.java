package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/employee";
        String userName = "root";
        String password = "root";

        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded successfully...");
        Connection con = DriverManager.getConnection(url,userName,password);
        System.out.println("connection estd.");

        String query = "update emp set salary =  salary + salary * 0.20";
        Statement stmt = con.createStatement();
        System.out.println("Number of rows updated : " + stmt.executeUpdate(query));

        stmt.close();
        con.close();
    }
}
