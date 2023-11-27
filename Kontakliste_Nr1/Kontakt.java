public class Kontakt extends Kontaktliste {

    private String name;
    private String surname;
    private String number;

    //Konstruktor
    public Kontakt(String name, String surname, String number) {
        this.name = name;
        this.surname = surname;
        this.number = number;
    }

    //Getter-Methoden
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getNumber() {
        return number;
    }

    //Setter-Methoden
    public void setName(String newName) {
        this.name = newName;
    }
    public void setSurname(String newSurname) {
        this.surname = newSurname;
    }
    public void setNumber(String newNumber) {
        this.number = newNumber;
    }


}