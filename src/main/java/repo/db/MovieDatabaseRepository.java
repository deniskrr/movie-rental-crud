package repo.db;

import domain.Movie;
import domain.Movie;
import domain.Movie;
import domain.Validator.Validator;
import domain.Validator.ValidatorException;
import repo.IRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class MovieDatabaseRepository implements IRepository<UUID, Movie> {
    private static final String URL = "jdbc:postgresql://127.0.0.1:5433/Laboratory";
    private static final String USERNAME = System.getProperty("username");
    private static final String PASSWORD = System.getProperty("password");

    private Validator<Movie> validator;

    public MovieDatabaseRepository(Validator<Movie> validator) {
        this.validator = validator;
    }


    @Override
    public Optional<Movie> findOne(UUID uuid) {
        Movie movie = null;
        String sql = "select * from Movies" +
                " where id=?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String title = resultSet.getString("title");
                double rating = resultSet.getDouble("rating");
                int year = resultSet.getInt("year");
                String genre = resultSet.getString("genre");

                movie = new Movie(title, rating, year, genre);
                movie.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(movie);
    }

    @Override
    public Iterable<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        String sql = "select * from Movies";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String title = resultSet.getString("title");
                double rating = resultSet.getDouble("rating");
                int year = resultSet.getInt("year");
                String genre = resultSet.getString("genre");


                Movie movie = new Movie(title, rating, year, genre);
                movie.setId(id);
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        validator.validate(entity);

        String sql = "insert into movies(id,title, rating, year, genre) " +
                "values (?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, entity.getId().toString());
            statement.setString(2, entity.getTitle());
            statement.setDouble(3, entity.getRating());
            statement.setInt(4, entity.getYear());
            statement.setString(5, entity.getGenre());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(entity);
    }

    @Override
    public Optional<Movie> delete(UUID uuid) {
        String sql = "delete from movies " +
                "where id=?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, uuid.toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Movie> update(Movie entity) throws ValidatorException {
        String sql = "update movie set title=?, rating=?, year=?, genre=? where id=?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, entity.getTitle());
            statement.setDouble(2, entity.getRating());
            statement.setInt(3, entity.getYear());
            statement.setString(4, entity.getGenre());
            statement.setString(5, entity.getId().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(entity);
    }
}
