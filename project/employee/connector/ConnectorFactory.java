package project.employee.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorFactory {
    static Connection con =  null;
    static String url = "jdbc:mysql://localhost:3306/employee";
    static String userName = "root";
    static String password = "root";

    public static Connection requestConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,userName,password);
        return con;
    }
}
