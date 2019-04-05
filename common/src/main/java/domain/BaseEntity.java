package domain;

import java.util.UUID;

/**
 * Superclass for every entity from the application.
 */
public class BaseEntity {
    private UUID id;

    public BaseEntity() {
        id = UUID.randomUUID();
    }

    /**
     * Returns the ID of the entity.
     * @return the ID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the ID of the entity
     * @param id of the entity
     */
    public void setId(UUID id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}