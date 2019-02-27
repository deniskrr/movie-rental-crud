package main.java.domain.Validator;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}