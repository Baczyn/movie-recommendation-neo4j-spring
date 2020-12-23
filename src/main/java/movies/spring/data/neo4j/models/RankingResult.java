package movies.spring.data.neo4j.models;

public class RankingResult {
    private Float rating;
    private Movie movie;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    private String Title;

    public RankingResult(Movie movie, Float rating) {
        this.rating = rating;
        this.movie = movie;
    }

    public RankingResult() {
    }

    public RankingResult(String title, Float rating) {
        this.rating = rating;
        this.movie = new Movie();
        this.movie.setTitle(title);
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
