// import modules & librarys
import java.sql.*;
import java.util.Properties;

public class DbSetup {
    Connection dbConn;

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

            // return connection obj
            return dbConn;
        } catch (ClassNotFoundException | SQLException ex) {
            // print failure status
            ex.printStackTrace();

            // return nothing if connection cant be established
            return null;
        }
    }
}