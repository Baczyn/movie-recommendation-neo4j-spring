package movies.spring.data.neo4j.service;

import movies.spring.data.neo4j.models.Movie;
import movies.spring.data.neo4j.models.RankingResult;
import movies.spring.data.neo4j.repository.MovieRepository;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    private final Driver driver;

    MovieService(MovieRepository movieRepository, Driver driver) {
        this.movieRepository = movieRepository;
        this.driver = driver;
    }

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public RankingResult rankedMovie() {
        return null;
    }

    public List<RankingResult> getRankedBy(String name) {
        List<RankingResult> result = new ArrayList<>();

        try (Session session = driver.session()) {
            var records = session.readTransaction(tx -> tx.run("MATCH (MB_Person { name: $name })-[rating]-(movie) RETURN movie.title as title, movie.director as director, movie.released as released, rating.rating as rating", parameters("name", name)).list());
            records.forEach(r -> {
                result.add(new RankingResult(new Movie(r.get("title").toString(),r.get("director").asString(),r.get("released").asInt()), r.get("rating").asFloat()));
            });
        }
        return result;
    }


    public List<RankingResult> getRanking() {
        List<RankingResult> result = new ArrayList<>();

        try (Session session = driver.session()) {
            var records = session.readTransaction(tx -> tx.run("MATCH (m:MB_Movie )<-[MB_RATED]-(MB_Person)" +
                    "RETURN m.title as title, m.director as director, m.released as released, avg(MB_RATED.rating) as best order by best DESC\n").list());
            records.forEach(r -> {
                result.add(new RankingResult(new Movie(r.get("title").toString(),r.get("director").asString(),r.get("released").asInt()), r.get("best").asFloat()));
            });
        }
        return result;

    }
}
