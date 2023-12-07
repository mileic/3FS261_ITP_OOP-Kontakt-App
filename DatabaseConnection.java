import java.sql.*;

public class DatabaseConnection {
    public static void main(String[] args) {
        // declare and initiate variables
        String url = "jbdc:mysql://localhost:3306/ContactsDB";
        String username = "root";
        String password = "K1nG$m3n*"; 
        String databaseName = "ContactsDB";

        try {
            // try to connect to database with JBDC driver. 
            // Class.forName("com.mysql.cj.jbdc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "USE DATABASE" + databaseName;

            Statement statement = con.createStatement(); 
            statement.executeUpdate(sql);

            // close connection to database 
            con.close();
        
        } catch (Exception e) {
            // connection not sucessful -> print error
            System.out.println(e);
        }
    } 
}