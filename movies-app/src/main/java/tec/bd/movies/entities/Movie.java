package tec.bd.movies.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie {

    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/YYYY");

    private int movieId;
    private String title;
    private Date releaseDate;
    private Category category;

    public Movie() {

    }

    public Movie(int movieId, String title, Date releaseDate, Category category) {
        this.movieId = movieId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.category = category;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getReleaseDateOnly() {
        return DATE_FORMATTER.format(this.releaseDate);
    }
}
