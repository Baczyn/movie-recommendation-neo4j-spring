package movies.spring.data.neo4j.repository;

import movies.spring.data.neo4j.models.Movie;
import movies.spring.data.neo4j.models.RankingResult;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(path = "movies")
public interface MovieRepository extends Repository<Movie, Long> {

    List<Movie> findAll();

    List<RankingResult> getRankedBy(String name);

}
