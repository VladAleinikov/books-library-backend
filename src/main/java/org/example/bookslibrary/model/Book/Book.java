package org.example.bookslibrary.model.Book;

import jakarta.persistence.*;
import org.example.bookslibrary.model.Author.Author;
import org.example.bookslibrary.model.Genre.Genre;
import org.example.bookslibrary.model.Review.Review;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;

    @ManyToMany
    @JoinTable(name="BooksToGenres",
            joinColumns = @JoinColumn(name = "bookId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genreId", referencedColumnName = "id"))
    private Set<Genre> genres = new HashSet<>();

    private String description;

    private String coverImgUrl;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="bookId")
    private Set<Review> reviews = new HashSet<>();

    private float avgRating;

    public Book() {
    }

    public Book(String title, Author author, Set<Genre> genres, String description, String coverImgUrl, Set<Review> reviews, float avgRating) {
        this.title = title;
        this.author = author;
        this.genres = genres;
        this.description = description;
        this.coverImgUrl = coverImgUrl;
        this.reviews = reviews;
        this.avgRating = avgRating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", genres=" + genres +
                ", description='" + description + '\'' +
                ", coverImgUrl='" + coverImgUrl + '\'' +
                ", reviews=" + reviews +
                ", avgRating=" + avgRating +
                '}';
    }
}
