package repo.db;

import domain.Movie;
import domain.Validator.Validator;
import domain.Validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import repo.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class MovieDatabaseRepository implements Repository<UUID, Movie> {
    @Autowired
    private JdbcOperations jdbcOperations;

    private Validator<Movie> validator;

    public MovieDatabaseRepository(Validator<Movie> validator) {
        this.validator = validator;
    }


//    @Override
//    public Optional<Movie> findOne(UUID uuid) {
//        Movie movie = null;
//        String sql = "select * from Movies" +
//                " where id=?";
//
//        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
//                PASSWORD)) {
//
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, uuid.toString());
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                UUID id = UUID.fromString(resultSet.getString("id"));
//                String title = resultSet.getString("title");
//                double rating = resultSet.getDouble("rating");
//                int year = resultSet.getInt("year");
//                String genre = resultSet.getString("genre");
//
//                movie = new Movie(title, rating, year, genre);
//                movie.setId(id);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return Optional.ofNullable(movie);
//    }

    @Override
    public List<Movie> findAll() {
        String sql = "select * from Movies";

        return jdbcOperations.query(sql, (rs, rowNum) -> {
            UUID id = UUID.fromString(rs.getString("id"));
            String title = rs.getString("title");
            double rating = rs.getDouble("rating");
            int year = rs.getInt("year");
            String genre = rs.getString("genre");

            Movie movie = new Movie(title, rating, year, genre);
            movie.setId(id);
            return movie;
        });
    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        validator.validate(entity);

        String sql = "insert into movies(id,title, rating, year, genre) " +
                "values (?,?,?,?,?)";
        try {
            jdbcOperations.update(
                    sql, entity.getId(), entity.getTitle(), entity.getRating(), entity.getYear(), entity.getGenre());
            return Optional.of(entity);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> delete(UUID uuid) {
        String sql = "delete from movies " +
                "where id=?";
        try {
            jdbcOperations.update(sql, uuid.toString());
            return Optional.of(true);
        } catch (DataAccessException e) {
            return Optional.of(false);
        }
    }

    @Override
    public Optional<Movie> update(Movie entity) throws ValidatorException {
        String sql = "update movie set title=?, rating=?, year=?, genre=? where id=?";
        try {
            jdbcOperations.update(
                    sql, entity.getTitle(), entity.getRating(), entity.getYear(), entity.getGenre(), entity.getId());
            return Optional.of(entity);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }
}
