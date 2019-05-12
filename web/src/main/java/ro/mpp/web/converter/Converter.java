package ro.mpp.web.converter;


import ro.mpp.core.domain.BaseEntity;
import ro.mpp.web.dto.BaseDto;

/**
 * Created by radu.
 */

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {
    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);
}