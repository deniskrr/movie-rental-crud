package ro.mpp.web.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class MovieDto extends BaseDto {
    private String title;
    private double rating;
    private int year;
    private String genre;
}