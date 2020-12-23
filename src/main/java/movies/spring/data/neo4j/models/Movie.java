package movies.spring.data.neo4j.models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;


@Node("MB_Movie")
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String director;
    private Integer released;

    public Movie() {
    }

    public Movie(String title, String director, Integer released) {
        this.title = title;
        this.director = director;
        this.released = released;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getReleased() {
        return released;
    }

    public void setReleased(Integer released) {
        this.released = released;
    }
}
