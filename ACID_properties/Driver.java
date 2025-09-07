package ACID_properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws SQLException,ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/employee";
        String userName = "root";
        String password = "root";

        //step 1
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded successfully...");

        //step 2
        Connection con = DriverManager.getConnection(url,userName,password);
        System.out.println("connection established...");

        String query = "insert into emp (`id`,`name`,`designation`,`salary`) values (?,?,?,?)";

        //step 3
        PreparedStatement pstmt = con.prepareStatement(query);

        Scanner scn =  new Scanner(System.in);
        System.out.println("Enter how many row to fill :");
        int n =  scn.nextInt();

        con.setAutoCommit(false);
        while (n-->0){
            System.out.println("Enter id :");
            int id = scn.nextInt();
            System.out.println("Enter name :");
            String name = scn.next();
            System.out.println("Enter designation :");
            String designation = scn.next();
            System.out.println("Enter salary :");
            int salary = scn.nextInt();

            pstmt.setInt(1,id);
            pstmt.setString(2,name);
            pstmt.setString(3,designation);
            pstmt.setInt(4,salary);
        }
        con.commit();

        scn.close();
        pstmt.close();
        con.close();
    }
}
