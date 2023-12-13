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
        String query = "SELECT id, givenName, surname, phoneNumber FROM Contacts ORDER BY id ASC";

        try (PreparedStatement statement = dbConn.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
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

    // method to get the maximum id
    public static int getMaxIdFromDatabase(Connection dbConn) {
        int maxId = -1; // returns null if no entries are found

        try {
            String query = "SELECT MAX(id) FROM Contacts";
            try (PreparedStatement statement = dbConn.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // get the maximum id from the result set
                    maxId = resultSet.getInt(1);
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace(); // Handle the exception appropriately
        }
        return maxId;
    }

    // method to update the database by newly written rows
    public static void alterContacts(Connection dbConn, String id, String givenName, String surname, String phoneNumber) {
        // prepare query statement
        String query = "SELECT * FROM Contacts WHERE id = ?";

        try {
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1, id);

            System.out.println(statement);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    createContact(dbConn, givenName, surname, phoneNumber);
                } else {
                    updateContact(dbConn, id, givenName, surname, phoneNumber);
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace(); // catch sql exception
        }
    }

    public static void createContact(Connection dbConn, String givenName, String surname, String phoneNumber) {
        // prepare query statement
        String query = "INSERT INTO Contacts (givenName, surname, phoneNumber) VALUES (?, ?, ?)";

        try (PreparedStatement statement = dbConn.prepareStatement(query)) {
            // set parameters
            statement.setString(1, givenName);
            statement.setString(2, surname);
            statement.setString(3, phoneNumber);

            // execute update
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Neuer Eintrag wurde erstellt.");
            } else {
                System.out.println("Fehler beim Erstellen des Eintrags.");
            }

            // close ressources
            statement.close();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace(); // catch sql exception
        }
    }

    public static void updateContact(Connection dbConn, String id, String givenName, String surname, String phoneNumber) {
        // prepare query statement
        String query = "UPDATE Contacts SET givenName = ?, surname = ?, phoneNumber = ? WHERE id = ?";

        try (PreparedStatement statement = dbConn.prepareStatement(query)) {
            // set parameters
            statement.setString(1, givenName);
            statement.setString(2, surname);
            statement.setString(3, phoneNumber);
            statement.setString(4, id);

            // execute update
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Contact updated successfully.");
            } else {
                System.out.println("No contact updated.");
            }

            // close ressources
            statement.close();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace(); // catch sql exception
        }
    }

    public static void removeContact(Connection dbConn, int id) {
        // create query
        String query = "DELETE FROM Contacts WHERE id = ?";

        try (PreparedStatement statement = dbConn.prepareStatement(query)){
            // convert id to string for sql query
            String sId = Integer.toString(id);

            // replace params for first index (id)
            statement.setString(1, sId);
            statement.executeUpdate();

            // close ressources
            statement.close();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace(); // catch sql exception
        }
    }
}