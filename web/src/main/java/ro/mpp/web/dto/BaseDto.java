package ro.mpp.web.dto;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseDto implements Serializable {
    private Long id;
}
