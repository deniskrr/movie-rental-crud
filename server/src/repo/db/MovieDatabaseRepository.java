package repo.db;

import domain.Movie;
import domain.Validator.Validator;
import domain.Validator.ValidatorException;
import repo.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface MovieDatabaseRepository extends Repository<UUID, Movie> {

}