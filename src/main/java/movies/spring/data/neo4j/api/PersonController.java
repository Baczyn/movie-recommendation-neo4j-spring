package movies.spring.data.neo4j.api;

import movies.spring.data.neo4j.models.Person;
import movies.spring.data.neo4j.models.RankingResult;
import movies.spring.data.neo4j.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v0/people")
class PersonController {

    private final PersonService personService;

    PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/all")
    List<Person> getAll() {
        return personService.getAll();
    }

    @PostMapping
    private Person savePerson(@RequestBody Person person){
        return personService.savePerson(person);
    }

    @PostMapping("/{name}")
    private RankingResult rateMovie(@PathVariable String name, @RequestBody RankingResult rankingResult){
        return personService.rateMovie(name,rankingResult);
    }

}
