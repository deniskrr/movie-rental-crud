package ro.mpp.core.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

import lombok.*;

/**
 * Superclass for every entity from the application.
 */
@MappedSuperclass
@NoArgsConstructor
@Data
public class BaseEntity<ID> implements Serializable {
    @Id
    @GeneratedValue
    private ID id;


}