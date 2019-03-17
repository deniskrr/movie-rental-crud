package repo.db;

import domain.Movie;
import domain.Movie;
import domain.Validator.Validator;
import domain.Validator.ValidatorException;

import java.sql.*;
import java.util.Optional;
import java.util.UUID;


public class MovieDatabaseRepository extends DatabaseRepository<Movie> {
    public MovieDatabaseRepository(Validator<Movie> validator) {
        super(validator);
        this.loadData();
    }


    private void loadData() {
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5433/Laboratory", "postgres",
                "2233");
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("SELECT id, title, rating, year, genre\n" +
                     "\tFROM public.movies;")) {
            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString(1));
                String title = rs.getString(2);
                double rating = Double.valueOf(rs.getString(3));
                int year = Integer.valueOf(rs.getString(4));
                String genre = rs.getString(5);

                Movie movie = new Movie(title, rating, year, genre);
                movie.setId(id);

                super.save(movie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        Optional<Movie> optional = super.save(entity);

        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5433/Laboratory", "postgres",
                "2233");
             PreparedStatement statement = con.prepareStatement("INSERT INTO public.movies(\n" +
                     "\tid, title, rating, year, genre)\n" +
                     "\tVALUES (?, ?, ?, ?, ?);" +
                     "")
        ) {
            statement.setString(1, entity.getId().toString());
            statement.setString(2, entity.getTitle());
            statement.setDouble(3, entity.getRating());
            statement.setInt(4, entity.getYear());
            statement.setString(5, entity.getGenre());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return optional;
    }
}
