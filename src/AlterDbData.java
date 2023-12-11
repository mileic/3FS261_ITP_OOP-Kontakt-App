// import modules & librarys
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlterDbData {
    public static List<Contacts> getAllContactsFromDb() {
        // init array
        List<Contacts> contactsList = new ArrayList<>();
        
        // prepare query statement
        String query = "SELECT id, givenName, surname, phoneNumber FROM Contacts";
        
        try (PreparedStatement preparedStatement = DbSetup.dbConnection().prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String givenName = resultSet.getString("givenName");
                    String surname = resultSet.getString("surname");
                    String phoneNumber = resultSet.getString("phoneNumber");

                    Contacts contact = new Contacts(id, givenName, surname, phoneNumber);
                    contactsList.add(contact);
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return contactsList;
    }
}