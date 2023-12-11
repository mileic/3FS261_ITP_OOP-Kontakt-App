// import modules & librarys
import java.sql.*;
import java.util.Properties;
import javax.swing.JOptionPane;

public class DatabaseSetup {
    public static Connection dbConnection() {
        // ensure conn is null
        Connection dbConn = null;

        try {
            // declare variables
            String url = "jdbc:mysql://localhost:3306/ContactsDB";
            Properties dbProp = new Properties();
            dbProp.put("user", "root");
            dbProp.put("password", "K1nG$m3N*");

            // try to connect to database with JBDC driver.
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConn = DriverManager.getConnection(url, dbProp);

            if (dbConn != null) {
                // show success status
                JOptionPane.showMessageDialog(null, "Connected to the database.");
            }
            // return connection obj
            return dbConn;
        } catch (Exception ex) {
            // print failure status
            System.out.println(ex);

            String errOut = "Connection to database failed.\n\n" + ex;
            // show failure status
            JOptionPane.showMessageDialog(null, errOut);
            
            return null;
        }
    }
}