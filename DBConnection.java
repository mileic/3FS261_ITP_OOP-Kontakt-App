import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JOptionPane;

public class DBConnection {
    public static Connection dbConnection() {
        // declare and initiate variables
        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseName = "ContactsDB";
        String url = "jbdc:mysql://localhost:3306/" + databaseName;
        String username = "root";
        String password = "K1nG$m3n*";

        try {
            // try to connect to database with JBDC driver. 
            // Class.forName("com.mysql.cj.jbdc.Driver");
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);

            // show status
            JOptionPane.showMessageDialog(null, "Connected to the database");

            // return connection
            return conn;
        } catch (Exception e) {
            // connection not sucessful -> print error
            System.out.println(e);

            JOptionPane.showMessageDialog(null, "Could not connect to the database...");
            return null;
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DBConnection.dbConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}