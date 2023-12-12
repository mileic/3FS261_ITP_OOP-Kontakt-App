// import modules & librarys
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlterDbData {
    // method to fetch all contacts and return an array of objects
    public static List<Contacts> fetchContacts(Connection dbConn) {
        // init array
        List<Contacts> contactsList = new ArrayList<>();

        // prepare query statement
        String query = "SELECT id, givenName, surname, phoneNumber FROM Contacts";

        try (PreparedStatement preparedStatement = dbConn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // asign data form db to vars
                    int id = resultSet.getInt("id");
                    String givenName = resultSet.getString("givenName");
                    String surname = resultSet.getString("surname");
                    String phoneNumber = resultSet.getString("phoneNumber");

                    // create object from vars
                    Contacts contact = new Contacts(id, givenName, surname, phoneNumber);
                    contactsList.add(contact); // add object to array
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace(); // catch sql exception
        }
        return contactsList;
    }

    // method to update the database by newly written rows
    public static void setContacts(Connection dbConn, String givenName, String surname, String phoneNumber) {
        // prepare query statement
        String selectSql = "SELECT * FROM Contacts WHERE givenName = ? AND surname = ? AND phoneNumber = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(selectSql);
            // replace params for each index
            preparedStatement.setString(1, givenName);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, phoneNumber);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    // prepare sql statement
                    String insertSql = "INSERT INTO Contacts (givenName, surname, phoneNumber) VALUES (?, ?, ?)";
                    PreparedStatement insertStatement = dbConn.prepareStatement(insertSql);
                    // replace params for each index
                    insertStatement.setString(1, givenName);
                    insertStatement.setString(2, surname);
                    insertStatement.setString(3, phoneNumber);

                    int affectedRows = insertStatement.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("Neuer Eintrag wurde erstellt.");
                    } else {
                        System.out.println("Fehler beim Erstellen des Eintrags.");
                    }

                    // close ressources
                    insertStatement.close();
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace(); // catch sql exception
        }
    }

    public static void removeContact(Connection dbConn, int id) {
        // create query
        String removeSql = "DELETE FROM Contacts WHERE id = ?";

        try {
            // prepare sql statement
            PreparedStatement delStatement = dbConn.prepareStatement(removeSql);

            // convert id to string for sql query
            String sId = Integer.toString(id);

            // replace params for first index (id)
            delStatement.setString(1, sId);
            delStatement.executeUpdate();

            // close ressources
            delStatement.close();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace(); // catch sql exception
        }
    }
}