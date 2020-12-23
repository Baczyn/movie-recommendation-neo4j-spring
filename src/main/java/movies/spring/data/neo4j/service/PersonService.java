package movies.spring.data.neo4j.service;

import movies.spring.data.neo4j.models.Person;
import movies.spring.data.neo4j.models.RankingResult;
import movies.spring.data.neo4j.repository.PersonRepository;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.neo4j.driver.Values.parameters;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final Driver driver;

    PersonService(PersonRepository personRepository, Driver driver) {
        this.personRepository = personRepository;
        this.driver = driver;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public RankingResult rateMovie(String name, RankingResult rankingResult) {
        RankingResult result = rankingResult;

        try (Session session = driver.session()) {
            var recoeds = session.writeTransaction(tw -> tw.run(
                    "MATCH (p:MB_Person),(b:MB_Movie) " +
                            "WHERE p.name = $name AND b.title = $title " +
                            "CREATE (p)-[r:MB_RATED { rating: $rating}]->(b) " +
                            "RETURN type(r), r.rating",
                    parameters("name", name, "title", rankingResult.getTitle(), "rating", rankingResult.getRating())).list());
        }
        return result;
    }
}
