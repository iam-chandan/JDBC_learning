package transactionManagement;

import java.sql.*;
import java.util.Scanner;

public class TransactionManagement {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/tap_bank";
        String userName = "root";
        String password = "root";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully...");

            Connection con = DriverManager.getConnection(url,userName,password);
            System.out.println("connection estd.");

            Scanner scn = new Scanner(System.in);

            //Login Module
            System.out.println("<---- WELCOME TO TAP BANK ---->");
            System.out.println("Enter Account number :");
            int acc_num = scn.nextInt();
            System.out.println("Enter pin :");
            int pin = scn.nextInt();

            PreparedStatement pstmt1 = con.prepareStatement("select * from account where acc_num = ? and pin = ?");
            pstmt1.setInt(1,acc_num);
            pstmt1.setInt(2,pin);

            ResultSet res1 = pstmt1.executeQuery();
            res1.next();

            String name = res1.getString(2);
            int balance = res1.getInt(4);

            System.out.println("Welcome " + name);
            System.out.println("Available Balance is : " + balance);

            // Transfer module
            System.out.println("<---- Transfer details ---->");
            System.out.println("Enter Beneficiary Account number :");
            int bacc_num = scn.nextInt();
            System.out.println("Enter the transfer ammount :");
            int t_amount = scn.nextInt();

            con.setAutoCommit(false);
            Savepoint s = con.setSavepoint();
            PreparedStatement pstmt2 = con.prepareStatement("update account set balance =  balance - ? where acc_num = ?");
            pstmt2.setInt(1,t_amount);
            pstmt2.setInt(2,acc_num);
            pstmt2.executeUpdate();

            System.out.println("Incoming credit request...");
            System.out.println(name + " account no. " + acc_num + " wants to transfer " + t_amount);
            System.out.println("Press Y to receive and N to reject");
            String choice = scn.next();

            if(choice.equals("Y")){
                PreparedStatement pstmet3 = con.prepareStatement("update account set balance =  balance + ? where acc_num = ?");
                pstmet3.setInt(1,t_amount);
                pstmet3.setInt(2,bacc_num);
                pstmet3.executeUpdate();

                PreparedStatement pstmt4 = con.prepareStatement("select * from account where acc_num = ?");
                pstmt4.setInt(1,bacc_num);
                ResultSet res2 =  pstmt4.executeQuery();
                res2.next();
                System.out.println("Updated balance is : " + res2.getInt(4));
            }else {
                con.rollback(s);
                PreparedStatement pstmt5 = con.prepareStatement("select * from account where acc_num = ?");
                pstmt5.setInt(1,bacc_num);
                ResultSet res3 = pstmt5.executeQuery();
                res3.next();
                System.out.println("Existing balance is : " + res3.getInt(4));
            }
            con.commit();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
