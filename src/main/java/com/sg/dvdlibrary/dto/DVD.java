package com.sg.dvdlibrary.dto;

import java.util.Objects;

public class DVD {

    private final String title;
    private String releaseDate;
    private String mpaaRating;
    private String directorsName;
    private String studio;
    private String userRating;

    public DVD(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorsName() {
        return directorsName;
    }

    public void setDirectorsName(String directorsName) {
        this.directorsName = directorsName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DVD dvd = (DVD) o;
        return title.equals(dvd.title) && Objects.equals(releaseDate, dvd.releaseDate) && Objects.equals(mpaaRating, dvd.mpaaRating) && Objects.equals(directorsName, dvd.directorsName) && Objects.equals(studio, dvd.studio) && Objects.equals(userRating, dvd.userRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseDate, mpaaRating, directorsName, studio, userRating);
    }

    @Override
    public String toString() {
        return "DVD{" +
                "title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", mpaaRating='" + mpaaRating + '\'' +
                ", directorsName='" + directorsName + '\'' +
                ", studio='" + studio + '\'' +
                ", userRating='" + userRating + '\'' +
                '}';
    }
}
