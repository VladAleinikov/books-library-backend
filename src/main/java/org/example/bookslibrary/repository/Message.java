package org.example.bookslibrary.repository;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Messages")
public class Message {
    @Id
    private String id;

    private String text;

    private Date createdAt = new Date();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "senderId")
    private User sender;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receiverId")
    private User receiver;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shelveId")
    private Shelve shelve;

    public Message(String text, Date createdAt, User sender, User receiver, Shelve shelve) {
        this.text = text;
        this.createdAt = createdAt;
        this.sender = sender;
        this.receiver = receiver;
        this.shelve = shelve;
    }

    public Message() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Shelve getShelve() {
        return shelve;
    }

    public void setShelve(Shelve shelve) {
        this.shelve = shelve;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", shelve=" + shelve +
                '}';
    }
}
