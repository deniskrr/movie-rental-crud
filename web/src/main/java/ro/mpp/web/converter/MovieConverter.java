package ro.mpp.web.converter;

import ro.mpp.core.domain.Movie;
import ro.mpp.web.dto.MovieDto;

public class MovieConverter extends BaseConverter<Movie, MovieDto> {

    @Override
    public Movie convertDtoToModel(MovieDto dto) {
        Movie movie = Movie.builder()
                .title(dto.getTitle())
                .genre(dto.getGenre())
                .year(dto.getYear())
                .rating(dto.getRating())
                .build();
        movie.setId(dto.getId());

        return movie;
    }

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        if (movie != null) {
            MovieDto dto = MovieDto.builder()
                    .title(movie.getTitle())
                    .genre(movie.getGenre())
                    .year(movie.getYear())
                    .rating(movie.getRating())
                    .build();
            dto.setId(movie.getId());

            return dto;
        }
        return null;
    }
}
