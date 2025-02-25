package org.example.bookslibrary.model.Review;

import jakarta.persistence.*;
import org.example.bookslibrary.model.Book.Book;
import org.example.bookslibrary.model.FavoriteAuthors.FavoriteAuthors;
import org.example.bookslibrary.model.FavoriteGenres.FavoriteGenres;
import org.example.bookslibrary.model.User.User;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Reviews")
public class Review {

    @Id
    private String userId;

    @Id
    private String bookId;

    private byte rating;

    private String text;

    private Date createdAt = new Date();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookId")
    private Book book;

    @PostPersist
    public void onCreate() {
        int ratingSum = 0;
        int reviewsLength = book.getReviews().size();

        for (Review review : book.getReviews()) {
            ratingSum += review.getRating();
        }

        book.setAvgRating((float) ratingSum / reviewsLength);


        FavoriteAuthors favoriteAuthor = user.findFavoriteAuthor(book.getAuthor());
        Set<FavoriteGenres> favoriteGenres = user.findFavoriteGenres(book.getGenres());

        favoriteGenres.forEach(favoriteGenre -> favoriteGenre.changeRatingByReview(rating));
        favoriteAuthor.changeRatingByReview(rating);
    }

    public Review() {
    }

    public Review(byte rating, String text, Date createdAt, User user, Book book) {
        this.rating = rating;
        this.text = text;
        this.createdAt = createdAt;
        this.user = user;
        this.book = book;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Review{" +
                "userId='" + userId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", rating='" + rating + '\'' +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
