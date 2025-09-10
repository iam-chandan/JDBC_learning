package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    public static void main(String[] args) throws SQLException,ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/employee";
        String userName = "root";
        String password = "root";

        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded successfully..");

        Connection con = DriverManager.getConnection(url,userName,password);
        System.out.println("connection estd.");

        String quesry = "delete from emp where name = 'priya'";
        Statement stmt = con.createStatement();
        System.out.println("Number of rows deleted : " + stmt.executeUpdate(quesry));

        stmt.close();
        con.close();
    }
}
