package domain;

public class Rental extends BaseEntity {
    private Client client;
    private Movie movie;

    public Rental(Client client, Movie movie) {
        super();
        this.client = client;
        this.movie = movie;
    }

    public Client getClient() {
        return client;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "Client: " + client + " --- Movie: " + movie;
    }
}
