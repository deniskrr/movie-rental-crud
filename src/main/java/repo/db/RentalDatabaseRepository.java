package repo.db;

import domain.Rental;
import domain.Rental;
import domain.Validator.Validator;
import domain.Validator.ValidatorException;

import java.sql.*;
import java.util.Optional;
import java.util.UUID;

public class RentalDatabaseRepository extends DatabaseRepository<Rental> {
    public RentalDatabaseRepository(Validator<Rental> validator) {
        super(validator);
        this.loadData();
    }


    private void loadData() {
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5433/Laboratory", "postgres",
                "2233");
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("SELECT id, client_id, movie_id\n" +
                     "\tFROM public.rentals;")) {
            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString(1));
                UUID client_id = UUID.fromString(rs.getString(2));
                UUID movie_id = UUID.fromString(rs.getString(3));


                Rental rental = new Rental(client_id, movie_id);
                rental.setId(id);

                super.save(rental);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Rental> save(Rental entity) throws ValidatorException {
        Optional<Rental> optional = super.save(entity);

        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5433/Laboratory", "postgres",
                "2233");
             PreparedStatement statement = con.prepareStatement("INSERT INTO public.rentals(\n" +
                     "\tid, client_id, movie_id)\n" +
                     "\tVALUES (?, ?, ?);")
        ) {
            statement.setString(1, entity.getId().toString());
            statement.setString(2, entity.getClientID().toString());
            statement.setString(3, entity.getMovieID().toString());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return optional;
    }
}
