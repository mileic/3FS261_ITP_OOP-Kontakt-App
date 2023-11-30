import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {
        
        // declare veriables 
        String url = "jdbc:mysql://localhost:3306/databasecontacts";
        String username = "root";
        String password = "Sportartikel-123";
        Connection connection = null; 

        try {
           // try to connect
            System.out.println("Connecting database...");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
            
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!");
            e.printStackTrace();
        } finally {
            // close connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                
                }
            }
        }
    }
}