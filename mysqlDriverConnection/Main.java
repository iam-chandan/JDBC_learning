package mysqlDriverConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException , SQLException {

        String url = "jdbc:mysql://localhost:3306/employee";
        String userName = "root";
        String password = "root";
//        try {
            //step 1 :- register the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver successfully loaded....");

            //step 2 :-
            Connection con = DriverManager.getConnection(url,userName,password);
            System.out.println("Connection established....");

            //step 3 :-
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,0);
            String query = "select * from emp";

            //step 4 :-
            ResultSet res = stmt.executeQuery(query);
            System.out.println("Query executed ....");

            ResultSetMetaData metaData = res.getMetaData();
//            System.out.println(res.getMetaData().getColumnCount());
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                System.out.println(metaData.getColumnName(i) + " : " + res.getMetaData().getColumnTypeName(i));
            }

            System.out.println("*********************");
            while (res.next()){
                System.out.println(res.getInt("id") + " " + res.getString("name") + " "
                        + res.getString("designation") + " " + res.getInt("salary"));
            }

            System.out.println("********************");
            res.absolute(3);
            System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getString(3) + " " + res.getInt(4));

//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}