package org.example.bookslibrary.model.Shelve;

import jakarta.persistence.*;
import org.example.bookslibrary.model.Library.Library;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Shelves")
public class Shelve {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String name;

    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ShelvesBooks",
            joinColumns = @JoinColumn(name = "shelveId", referencedColumnName = "id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "bookId", referencedColumnName = "bookId"),
                    @JoinColumn(name = "userId", referencedColumnName = "userId")
            }
    )
    private Set<Library> books = new HashSet<Library>();

    public void addBook(Library book){
        books.add(book);
        book.getShelves().add(this);
    }

    public void removeBook(Library book){
        this.books.remove(book);
        book.getShelves().remove(this);
    }

    public Shelve() {
    }

    public Shelve(String name, String description, Set<Library> books) {
        this.name = name;
        this.description = description;
        this.books = books;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Library> getBooks() {
        return books;
    }

    public void setBooks(Set<Library> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Shelve{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", books=" + books +
                '}';
    }
}
