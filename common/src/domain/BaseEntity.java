package domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

/**
 * Superclass for every entity from the application.
 */
@MappedSuperclass
public class BaseEntity<ID> implements Serializable {
    @Id
    @GeneratedValue
    private ID id;

    /**
     * Returns the ID of the entity.
     * @return the ID
     */
    public ID getId() {
        return id;
    }

    /**
     * Sets the ID of the entity
     * @param id of the entity
     */
    public void setId(ID id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}