package domain;

public class Client extends BaseEntity {
    private String firstName;
    private String lastName;
    private int year_of_birth;

    public Client(String firstName, String lastName, int year_of_birth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.year_of_birth = year_of_birth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getYear_of_birth() {
        return year_of_birth;
    }
}
