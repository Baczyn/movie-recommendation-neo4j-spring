package movies.spring.data.neo4j.repository;

import movies.spring.data.neo4j.models.Person;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "people")
public interface PersonRepository extends Repository<Person, Long> {

	List<Person> findAll();

	Person save(Person person);

}
