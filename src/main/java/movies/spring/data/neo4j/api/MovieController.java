package movies.spring.data.neo4j.api;

import movies.spring.data.neo4j.models.Movie;
import movies.spring.data.neo4j.models.RankingResult;
import movies.spring.data.neo4j.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v0/movies")
class MovieController {

    private final MovieService movieService;

    MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    List<Movie> getAll() {
        return movieService.getAll();
    }

    //wyświetl oceny użytkownika name
    @GetMapping("/ranked/{name}")
    List<RankingResult> getRankedBy(@PathVariable String name) {
        return movieService.getRankedBy(name);
    }

    @GetMapping("/ranking")
    List<RankingResult> getRanking() {
        return movieService.getRanking();
    }


}
