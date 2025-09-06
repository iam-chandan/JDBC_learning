package mysqlDriverConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException , SQLException {

        String url = "jdbc:mysql://localhost:3306/grocery_db";
        String userName = "root";
        String password = "root";

        //step 1 :- register the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver successfully loaded....");

        //step 2 :-
        Connection con = DriverManager.getConnection(url,userName,password);
        System.out.println("Connection established....");
    }
}