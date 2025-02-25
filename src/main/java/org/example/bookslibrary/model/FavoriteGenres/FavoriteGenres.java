package org.example.bookslibrary.model.FavoriteGenres;

import jakarta.persistence.*;
import org.example.bookslibrary.model.Genre.Genre;
import org.example.bookslibrary.model.User.User;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "FavoriteGenres")
public class FavoriteGenres {

    @Id
    private String userId;

    @Id
    private String genreId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genreId")
    private Genre genre;

    @ColumnDefault("0")
    private int genreRating;

    public FavoriteGenres() {
    }

    public FavoriteGenres(User user, Genre genre, int genreRating) {
        this.user = user;
        this.genre = genre;
        this.genreRating = genreRating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public int getGenreRating() {
        return genreRating;
    }

    public void setGenreRating(int genreRating) {
        this.genreRating = genreRating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void increaseRatingByAdding() {
        genreRating += 3;
    }

    public void decreaseRatingByRemoving() {
        genreRating -= 3;
    }

    public void increaseRatingByFinishing() {
        genreRating += 6;
    }

    public void increaseRatingByFavoriting() {
        genreRating += 10;
    }

    public void decreaseRatingByUnfavoriting() {
        genreRating -= 10;
    }

    public void changeRatingByReview(byte reviewRate) {
        genreRating += reviewRate * 6 - 18;
    }

    @Override
    public String toString() {
        return "FavoriteGenres{" +
                "userId='" + userId + '\'' +
                ", genreId='" + genreId + '\'' +
                ", user=" + user +
                ", genre=" + genre +
                ", genreRating=" + genreRating +
                '}';
    }
}
