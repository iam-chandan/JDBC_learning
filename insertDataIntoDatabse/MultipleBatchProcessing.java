package insertDataIntoDatabse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MultipleBatchProcessing {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/employee";
        String userName = "root";
        String password = "root";

        //step 1
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded successfully");

        //step 2
        Connection con = DriverManager.getConnection(url,userName,password);
        System.out.println("connection estd.");

        //step 3
        String query = "insert into emp (`id`,`name`,`designation`,`salary`) values (?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(query);

        Scanner scn =  new Scanner(System.in);
        int n = scn.nextInt();

        while (n-->0){
            System.out.print("Enter ID: ");
            int id = scn.nextInt();
            scn.nextLine(); // consume newline

            System.out.print("Enter name: ");
            String name = scn.nextLine();

            System.out.print("Enter designation: ");
            String designation = scn.nextLine();

            System.out.print("Enter salary: ");
            int salary = scn.nextInt();
            scn.nextLine(); // consume newline

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, designation);
            pstmt.setInt(4, salary);

            pstmt.addBatch(); // add to batch
        }

        int[] batchLength = pstmt.executeBatch();
        System.out.println(batchLength.length + " employees inserted successfully!");
    }
}
