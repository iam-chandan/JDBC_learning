package insertDataIntoDatabse;

import java.sql.*;

public class Alpha {
    public static void main(String[] args) {
        String url =  "jdbc:mysql://localhost:3306/employee";
        String userName = "root";
        String password = "root";

        try{
            //step 1
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully ..");

            //step 2
            Connection con = DriverManager.getConnection(url,userName,password);
            System.out.println("connection estd.");

            //step 3
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,0);
            String query = "insert into emp (`id`,`name`,`designation`,`salary`) values (4,'priya','HR',15000)";
            stmt.execute(query);
            System.out.println("query executed successfully...");

            stmt.close();
            con.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
