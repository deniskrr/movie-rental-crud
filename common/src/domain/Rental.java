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

    public Rental() {
        super();
        this.clientID = UUID.randomUUID();
        this.movieID = UUID.randomUUID();
    }

    public UUID getClientID() {
        return clientID;
    }

    public UUID getMovieID() {
        return movieID;
    }

    public void setClientID(UUID clientID) {
        this.clientID = clientID;
    }

    public void setMovieID(UUID movieID) {
        this.movieID = movieID;
    }

    @Override
    public String toString() {
        return "Client: " + clientID + " Movie: " + movieID;
    }
}
