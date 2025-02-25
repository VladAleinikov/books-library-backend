package org.example.bookslibrary.model.Library;

import jakarta.persistence.*;
import org.example.bookslibrary.model.Book.Book;
import org.example.bookslibrary.model.FavoriteAuthors.FavoriteAuthors;
import org.example.bookslibrary.model.FavoriteGenres.FavoriteGenres;
import org.example.bookslibrary.model.Shelve.Shelve;
import org.example.bookslibrary.model.User.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Library")
public class Library {

    @Id
    private String userId;

    @Id
    private String bookId;

    private Status status;

    private int currentPage = 0;

    private Date addedAt;

    private Date startedAt;

    private Date finishedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookId")
    private Book book;

    @ManyToMany(mappedBy = "books")
    private Set<Shelve> shelves = new HashSet<Shelve>();

    @PrePersist
    public void onCreate() {
        status = Status.ADDED;
        addedAt = new Date();
    }

    @PreUpdate
    public void onUpdateBefore() {
        if (status == Status.ADDED) {
            startedAt = new Date();
            status = Status.IN_PROCESS;

            FavoriteAuthors favoriteAuthor = user.findFavoriteAuthor(book.getAuthor());
            Set<FavoriteGenres> favoriteGenres = user.findFavoriteGenres(book.getGenres());

            favoriteGenres.forEach(FavoriteGenres::increaseRatingByAdding);
            favoriteAuthor.increaseRatingByAdding();
        }
    }

    public Library() {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
            FavoriteAuthors favoriteAuthor = user.findFavoriteAuthor(book.getAuthor());
            Set<FavoriteGenres> favoriteGenres = user.findFavoriteGenres(book.getGenres());

        if (status == Status.FINISHED && this.status != Status.DELETED) {
            user.addBookToStatistics(book.getTitle(), book.getAuthor().getName());

            favoriteGenres.forEach(FavoriteGenres::increaseRatingByFinishing);
            favoriteAuthor.increaseRatingByFinishing();
        }
        else if(status == Status.DELETED){
            favoriteGenres.forEach(FavoriteGenres::decreaseRatingByRemoving);
            favoriteAuthor.decreaseRatingByRemoving();
        }

        this.status = status;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void nextPage() {
        currentPage += 1;
        user.addPageToStatistics(1);
    }

    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Set<Shelve> getShelves() {
        return shelves;
    }

    public void setShelves(Set<Shelve> shelves) {
        this.shelves = shelves;
    }

    @Override
    public String toString() {
        return "Library{" +
                "userId='" + userId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", status='" + status + '\'' +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
