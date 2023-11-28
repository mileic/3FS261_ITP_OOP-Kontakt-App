public class Contacts extends ContactList {

    private int id;
    private String givenName;
    private String surname;
    private String phoneNumber;
    
    // constructor class
    public Contacts(int id, String givenName, String surname, String phoneNumber){
        this.id = id;
        this.givenName = givenName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    // get methods
    public int getId(){
        return id;
    }
    public String getGivenName(){
        return givenName;
    }
    public String getSurname(){
        return surname;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

    // set methods
    public void setId(int newId) {
        this.id = newId;
    }
    public void setGivenName(String newGivenName) {
        this.givenName = newGivenName;
    }
    public void setSurname(String newSurname) {
        this.surname = newSurname;
    }
    public void setPhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }
}