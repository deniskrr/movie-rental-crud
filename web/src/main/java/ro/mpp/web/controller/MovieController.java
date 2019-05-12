package ro.mpp.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ro.mpp.core.service.MovieService;

@RestController
public class MovieController {
    private static final Logger log =
            LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

}
