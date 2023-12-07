import java.sql.*;

public class DatabaseConnection {
    public static void main(String[] args) {
        // declare and initiate variables
        String url = "jbdc:mysql://localhost:3306/databasecontacts";
        String username = "root";
        String password = "Sportartikel-123"; 

        try {
            // try to connect to database with JBDC driver. 
            Class.forName("com.mysql.cj.jbdc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);

            Statement statement = con.createStatement(); 
            ResultSet resultSet = statement.executeQuery("select * from contacts"); 

            
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+resultSet.getString(3)+resultSet.getString(4));
            }

            // close connection to database 
            con.close();
        
        } catch (Exception e) {
            // connection not sucessful -> print error
            System.out.println(e);
        }
    } 
}