package repo.db;

import domain.BaseEntity;
import domain.Validator.Validator;
import domain.Validator.ValidatorException;
import repo.InMemoryRepository;

import java.util.Optional;

public class DatabaseRepository<T extends BaseEntity> extends InMemoryRepository<T> {

    public DatabaseRepository(Validator<T> validator) {
        super(validator);
    }

}
