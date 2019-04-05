package domain;

import javax.persistence.Entity;
import java.util.UUID;


@Entity
public class Rental extends BaseEntity<Long> {
    private Long clientID;
    private Long movieID;

    public Rental(Long clientID, Long movieID) {
        this.clientID = clientID;
        this.movieID = movieID;
    }

    public Rental() {
    }

    public Long getClientID() {
        return clientID;
    }

    public Long getMovieID() {
        return movieID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public void setMovieID(Long movieID) {
        this.movieID = movieID;
    }

    @Override
    public String toString() {
        return "Client: " + clientID + " Movie: " + movieID;
    }
}
