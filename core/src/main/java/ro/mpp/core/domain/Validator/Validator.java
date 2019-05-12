package ro.mpp.core.domain.Validator;

/**
 * Interface used to validate entities.
 * @param <T> - the class of the entity to be validated
 */
public interface Validator<T> {
    /**
     * Validates an entity
     * @param entity to be validated
     * @throws ValidatorException - if the entity is not valid
     */
    void validate(T entity) throws ValidatorException;
}