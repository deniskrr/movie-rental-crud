package ro.mpp.core.domain;

import lombok.*;

import javax.persistence.Entity;

/**
 * Class representing a movie entity
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Movie extends BaseEntity<Long> {
    private String title;
    private double rating;
    private int year;
    private String genre;

}
