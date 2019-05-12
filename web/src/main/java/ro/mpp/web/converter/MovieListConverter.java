package ro.mpp.web.converter;

import ro.mpp.core.domain.Movie;
import ro.mpp.web.dto.MovieDto;
import ro.mpp.web.dto.MovieListDto;

import java.util.LinkedList;
import java.util.List;

public class MovieListConverter {

    public MovieListDto convertModelToDto(List<Movie> movies) {
        MovieListDto mlDto = new MovieListDto();
        List<MovieDto> moviesDto = new LinkedList<>();
        for (Movie movie : movies) {
            MovieDto dto = MovieDto.builder()
                    .title(movie.getTitle())
                    .genre(movie.getGenre())
                    .year(movie.getYear())
                    .rating(movie.getRating())
                    .build();
            dto.setId(movie.getId());
            moviesDto.add(dto);
        }
        mlDto.setMovies(moviesDto);
        return mlDto;
    }
}
