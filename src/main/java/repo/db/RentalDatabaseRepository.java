package repo.db;

import domain.Rental;
import domain.Rental;
import domain.Rental;
import domain.Validator.Validator;
import domain.Validator.ValidatorException;
import repo.IRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RentalDatabaseRepository implements IRepository<UUID, Rental> {
    private static final String URL = "jdbc:postgresql://127.0.0.1:5433/Laboratory";
    private static final String USERNAME = System.getProperty("username");
    private static final String PASSWORD = System.getProperty("password");

    private Validator<Rental> validator;

    public RentalDatabaseRepository(Validator<Rental> validator) {
        this.validator = validator;
    }


    @Override
    public Optional<Rental> findOne(UUID uuid) {
        Rental rental = null;
        String sql = "select * from Rentals" +
                " where id=?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                UUID client_id = UUID.fromString(resultSet.getString("client_id"));
                UUID movie_id = UUID.fromString(resultSet.getString("movie_id"));


                rental = new Rental(client_id, movie_id);
                rental.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(rental);
    }

    @Override
    public Iterable<Rental> findAll() {
        List<Rental> rentals = new ArrayList<>();
        String sql = "select * from Rentals";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                UUID client_id = UUID.fromString(resultSet.getString("client_id"));
                UUID movie_id = UUID.fromString(resultSet.getString("movie_id"));

                Rental rental = new Rental(client_id, movie_id);
                rental.setId(id);
                rentals.add(rental);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }

    @Override
    public Optional<Rental> save(Rental entity) throws ValidatorException {
        validator.validate(entity);

        String sql = "insert into rentals(id,client_id, movie_id) " +
                "values (?,?,?)";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, entity.getId().toString());
            statement.setString(2, entity.getClientID().toString());
            statement.setString(3, entity.getMovieID().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(entity);
    }

    @Override
    public Optional<Rental> delete(UUID uuid) {
        String sql = "delete from rentals " +
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
    public Optional<Rental> update(Rental entity) throws ValidatorException {
        String sql = "update rental set client_id=?, movie_id=? where id=?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, entity.getClientID().toString());
            statement.setString(2, entity.getMovieID().toString());
            statement.setString(3, entity.getId().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(entity);
    }
}
