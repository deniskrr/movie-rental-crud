package domain;

public class Client extends BaseEntity {
    private String firstName;
    private String lastName;
    private int yearOfBirth;

    public Client(String firstName, String lastName, int yearOfBirth) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
    }

    public Client() {
        super();
        this.firstName = "";
        this.lastName = "";
        this.yearOfBirth= 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return getId() + " " + getFirstName() + " " + getLastName() + " " + getYearOfBirth();
    }
}
