package org.example.bookslibrary.model.PagesReadStatistics;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "PagesReadStatistics")
public class PagesReadStatistics {

    @Id
    private LocalDate date;

    @Id
    private String userId;

    private int pagesRead;

    @PrePersist
    public void onCreate() {
        date = LocalDate.now();
    }

    public void increasePagesRead(int pages) {
        pagesRead += pages;
    }

    public PagesReadStatistics() {
    }

    public PagesReadStatistics(int pagesRead) {
        this.pagesRead = pagesRead;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPagesRead() {
        return pagesRead;
    }

    public void setPagesRead(int pagesRead) {
        this.pagesRead = pagesRead;
    }

    @Override
    public String toString() {
        return "PagesReadStatistics{" +
                "date=" + date +
                ", userId='" + userId + '\'' +
                ", pagesRead=" + pagesRead +
                '}';
    }
}
