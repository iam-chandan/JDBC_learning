package insertDataIntoDatabse;

import java.sql.*;
import java.util.Scanner;

public class AddBatch {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/employee";
        String userName = "root";
        String password = "root";

        try{
            //step 1
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully..");

            //step 2
            Connection  con = DriverManager.getConnection(url,userName,password);
            System.out.println("connection estd.");

            String query = "insert into emp (`id`,`name`,`designation`,`salary`) values (?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            Scanner scn = new Scanner(System.in);
            int id = scn.nextInt();
            scn.nextLine();
            String name = scn.nextLine();
            String designation = scn.nextLine();
            int salary = scn.nextInt();

            pstmt.setInt(1,id);
            pstmt.setString(2,name);
            pstmt.setString(3,designation);
            pstmt.setInt(4,salary);
            pstmt.addBatch();

            pstmt.executeBatch();
            System.out.println("query executed successfully...");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
