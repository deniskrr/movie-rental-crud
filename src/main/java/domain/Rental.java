package domain;

import java.util.UUID;

public class Rental extends BaseEntity {
    private UUID clientID;
    private UUID movieID;

    public Rental(UUID clientID, UUID movieID) {
        super();
        this.clientID = clientID;
        this.movieID = movieID;
    }

    public UUID getClientID() {
        return clientID;
    }

    public UUID getMovieID() {
        return movieID;
    }

    @Override
    public String toString() {
        return "Client: " + clientID + " Movie: " + movieID;
    }
}
