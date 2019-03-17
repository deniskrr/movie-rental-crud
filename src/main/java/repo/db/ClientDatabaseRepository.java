package repo.db;

import domain.Client;
import domain.Validator.Validator;
import domain.Validator.ValidatorException;

import java.sql.*;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDatabaseRepository extends DatabaseRepository<Client> {
    public ClientDatabaseRepository(Validator<Client> validator) {
        super(validator);
        this.loadData();
    }


    private void loadData() {
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5433/Laboratory", "postgres",
                "2233");
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("SELECT id, first_name, last_name, year\n" +
                     "\tFROM public.clients;")) {
            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString(1));
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                int year = Integer.valueOf(rs.getString(4));
                Client client = new Client(firstName, lastName, year);
                client.setId(id);
                super.save(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Client> save(Client entity) throws ValidatorException {
        Optional<Client> optional = super.save(entity);

        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5433/Laboratory", "postgres",
                "2233");
             PreparedStatement statement = con.prepareStatement("INSERT INTO public.clients(\n" +
                     "\tid, first_name, last_name, year)\n" +
                     "\tVALUES (?, ?, ?, ?);")
        ) {
            statement.setString(1, entity.getId().toString());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.setInt(4, entity.getYearOfBirth());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return optional;
    }
}
