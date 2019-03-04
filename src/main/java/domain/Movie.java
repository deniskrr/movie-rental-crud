package domain;

/**
 * Class representing a movie entity
 */
public class Movie extends BaseEntity {

    private String title;
    private double rating;
    private int year;

    public Movie(String title, double rating, int year) {
        super();
        this.title = title;
        this.rating = rating;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return getId() + " " + getTitle() + " (" + getYear() + ") " + getRating() + "/10";
    }
}
