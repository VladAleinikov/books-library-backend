package org.example.bookslibrary.repository;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "FavoriteAuthors")
public class FavoriteAuthors {

    @Id
    private String userId;

    @Id
    private String authorId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorId")
    private Author author;

    @ColumnDefault("0")
    private int authorRating;

    public FavoriteAuthors() {
    }

    public FavoriteAuthors(User user, Author author, int authorRating) {
        this.user = user;
        this.author = author;
        this.authorRating = authorRating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public int getAuthorRating() {
        return authorRating;
    }

    public void setAuthorRating(int authorRating) {
        this.authorRating = authorRating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void increaseRatingByAdding() {
        authorRating += 3;
    }

    public void decreaseRatingByRemoving() {
        authorRating -= 3;
    }

    public void increaseRatingByFinishing() {
        authorRating += 6;
    }

    public void increaseRatingByFavoriting() {
        authorRating += 10;
    }

    public void decreaseRatingByUnfavoriting() {
        authorRating -= 10;
    }

    public void changeRatingByReview(byte reviewRate) {
        authorRating += reviewRate * 6 - 18;
    }

    @Override
    public String toString() {
        return "FavoriteAuthors{" +
                "userId='" + userId + '\'' +
                ", authorId='" + authorId + '\'' +
                ", user=" + user +
                ", author=" + author +
                ", authorRating=" + authorRating +
                '}';
    }
}
