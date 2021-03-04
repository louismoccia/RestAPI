package restapi.models;

import java.util.Objects;

public class Dvd {
    private int dvdId;
    private String title;
    private int releaseYear;
    private String director;
    private String rating;
    private String notes;

    public Dvd(int dvdId, String title, int releaseYear, String director, String rating, String notes) {
        this.dvdId = dvdId;
        this.title = title;
        this.releaseYear = releaseYear;
        this.director = director;
        this.rating = rating;
        this.notes = notes;
    }

    public int getDvdId() {
        return dvdId;
    }

    public void setDvdId(int dvdId) {
        this.dvdId = dvdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dvd dvd = (Dvd) o;
        return dvdId == dvd.dvdId && releaseYear == dvd.releaseYear && title.equals(dvd.title) && director.equals(dvd.director) && rating.equals(dvd.rating) && Objects.equals(notes, dvd.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dvdId, title, releaseYear, director, rating, notes);
    }
}
