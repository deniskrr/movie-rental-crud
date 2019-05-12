package ro.mpp.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.mpp.core.domain.Movie;
import ro.mpp.core.service.MovieService;
import ro.mpp.web.converter.MovieConverter;
import ro.mpp.web.dto.MovieDto;
import ro.mpp.web.dto.MovieListDto;

import java.util.List;
import java.util.Set;


@RestController
public class MovieController {
    private static final Logger log =
            LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieConverter movieConverter;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    MovieListDto getAllMovies() {
        log.trace("getAllMovies - method called");

        List<Movie> movies = movieService.getAllMovies();
        List<MovieDto> dtos = movieConverter.convertModelsToDtos(movies);
        MovieListDto result = new MovieListDto(dtos);

        log.trace("getAllMovies - result={}", result);

        return result;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    MovieDto saveMovie(@RequestBody MovieDto dto) {
        log.trace("saveMovie: dto={}", dto);

        Movie saved = this.movieService.saveMovie(
                movieConverter.convertDtoToModel(dto)
        );
        MovieDto result = movieConverter.convertModelToDto(saved);

        log.trace("saveMovie: result={}", result);

        return result;
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    MovieDto updateMovie(@PathVariable Long id,
                         @RequestBody MovieDto dto) {
        log.trace("updateMovie - id={},dto={}", id, dto);

        Movie update = movieService.updateMovie(
                id,
                movieConverter.convertDtoToModel(dto));
        MovieDto result = movieConverter.convertModelToDto(update);

        return result;
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        log.trace("deleteMovie: id={}", id);

        movieService.deleteMovie(id);

        log.trace("deleteMovie --- method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}