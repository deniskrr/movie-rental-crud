package repo.db;

import domain.Movie;
import repo.Repository;

import java.util.UUID;


public interface MovieDatabaseRepository extends Repository<UUID, Movie> {

}
