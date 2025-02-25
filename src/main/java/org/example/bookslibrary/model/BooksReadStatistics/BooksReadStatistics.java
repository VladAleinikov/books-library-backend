package org.example.bookslibrary.model.BooksReadStatistics;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "BooksReadStatistics")
public class BooksReadStatistics {

    @Id
    private LocalDate finishedAt;

    @Id
    private String userId;

    private String bookName;

    private String author;

    public BooksReadStatistics() {
    }

    public BooksReadStatistics(String bookName, String author) {
        this.bookName = bookName;
        this.author = author;
    }

    public LocalDate getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDate finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BooksReadStatistics{" +
                "finishedAt=" + finishedAt +
                ", userId='" + userId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
