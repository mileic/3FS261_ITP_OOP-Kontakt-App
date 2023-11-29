import java.util.List;

public class Contacts {

    private int id;
    private String givenName;
    private String surname;
    private String phoneNumber;
    
    // constructor class
    public Contacts(int id, String givenName, String surname, String phoneNumber) {
        this.id = id;
        this.givenName = givenName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    // get methods
    public int getId() {
        return id;
    }
    public String getGivenName() {
        return givenName;
    }
    public String getSurname() {
        return surname;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Contacts> createDataList() {
        return List.of(
            new Contacts(1,  "Michael", "Leichtl", "Null"),
            new Contacts(2,  "Max", "Schwaderer", "Null")
        );
    }
}