package org.example.bookslibrary.model.User;

import jakarta.persistence.*;
import org.example.bookslibrary.model.Author.Author;
import org.example.bookslibrary.model.Book.Book;
import org.example.bookslibrary.model.BooksReadStatistics.BooksReadStatistics;
import org.example.bookslibrary.model.FavoriteAuthors.FavoriteAuthors;
import org.example.bookslibrary.model.FavoriteGenres.FavoriteGenres;
import org.example.bookslibrary.model.Genre.Genre;
import org.example.bookslibrary.model.Library.Library;
import org.example.bookslibrary.model.PagesReadStatistics.PagesReadStatistics;
import org.example.bookslibrary.model.Shelve.Shelve;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String oauth2Id;

    private String name;

    @Column(unique = true)
    private String email;

    private String avatarUrl;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private Set<Library> library = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="userId")
    private Set<Shelve> shelves = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="Favorites",
    joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "bookId", referencedColumnName = "id"))
    private Set<Book> favorites = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private Set<PagesReadStatistics> pagesReadStatistics = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private Set<BooksReadStatistics> booksReadStatistics = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private Set<FavoriteGenres> favoriteGenres = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private Set<FavoriteAuthors> favoriteAuthors = new HashSet<>();

    public User() {
    }

    public User(String name, String email, String avatarUrl, Set<Library> library, Set<Shelve> shelves, Set<Book> favorites, Set<PagesReadStatistics> pagesReadStatistics, Set<BooksReadStatistics> booksReadStatistics, Set<FavoriteGenres> favoriteGenres, Set<FavoriteAuthors> favoriteAuthors) {
        this.name = name;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.library = library;
        this.shelves = shelves;
        this.favorites = favorites;
        this.pagesReadStatistics = pagesReadStatistics;
        this.booksReadStatistics = booksReadStatistics;
        this.favoriteGenres = favoriteGenres;
        this.favoriteAuthors = favoriteAuthors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOauth2Id() {
        return oauth2Id;
    }

    public void setOauth2Id(String oauth2Id) {
        this.oauth2Id = oauth2Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Set<Shelve> getShelves() {
        return shelves;
    }

    public void setShelves(Set<Shelve> shelves) {
        this.shelves = shelves;
    }

    public Set<Book> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Book> favorites) {
        this.favorites = favorites;
    }

    public void addFavorite(Book book){
        FavoriteAuthors favoriteAuthor = findFavoriteAuthor(book.getAuthor());
        Set<FavoriteGenres> favoriteGenres = findFavoriteGenres(book.getGenres());

        favoriteGenres.forEach(FavoriteGenres::increaseRatingByFavoriting);
        favoriteAuthor.increaseRatingByFavoriting();

        favorites.add(book);
    }

    public void removeFavorite(Book book){
        FavoriteAuthors favoriteAuthor = findFavoriteAuthor(book.getAuthor());
        Set<FavoriteGenres> favoriteGenres = findFavoriteGenres(book.getGenres());

        favoriteGenres.forEach(FavoriteGenres::decreaseRatingByUnfavoriting);
        favoriteAuthor.decreaseRatingByUnfavoriting();

        favorites.remove(book);
    }

    public Set<PagesReadStatistics> getPagesReadStatistics() {
        return pagesReadStatistics;
    }

    public void setPagesReadStatistics(Set<PagesReadStatistics> pagesReadStatistics) {
        this.pagesReadStatistics = pagesReadStatistics;
    }

    public void addPageToStatistics(int page){
        LocalDate date = LocalDate.now();
        for (PagesReadStatistics statistics: pagesReadStatistics){
            if(statistics.getDate().equals(date)){
                statistics.increasePagesRead(page);
                return;
            }
        }

        pagesReadStatistics.add(new PagesReadStatistics(page));
    }

    public Set<BooksReadStatistics> getBooksReadStatistics() {
        return booksReadStatistics;
    }

    public void setBooksReadStatistics(Set<BooksReadStatistics> booksReadStatistics) {
        this.booksReadStatistics = booksReadStatistics;
    }

    public void addBookToStatistics(String bookName, String author){
        booksReadStatistics.add(new BooksReadStatistics(bookName, author));
    }

    public Set<Library> getLibrary() {
        return library;
    }

    public void setLibrary(Set<Library> library) {
        this.library = library;
    }

    public Set<FavoriteGenres> getFavoriteGenres() {
        return favoriteGenres;
    }

    public Set<FavoriteGenres> findFavoriteGenres(Set<Genre> bookGenres){
        Set<FavoriteGenres> foundGenres = new HashSet<>();

        for (Genre bookGenre: bookGenres){
            FavoriteGenres favoriteGenre = null;

            for (FavoriteGenres genre : favoriteGenres) {
                if (genre.getGenre().equals(bookGenre)) {
                    favoriteGenre = genre;
                    break;
                }
            }

            if (favoriteGenre == null) {
                favoriteGenre = new FavoriteGenres(this, bookGenre, 0);
                addFavoriteGenre(favoriteGenre);
            }

            favoriteGenres.add( favoriteGenre);
        }

        return foundGenres;
    }

    public void setFavoriteGenres(Set<FavoriteGenres> favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }

    public void addFavoriteGenre(FavoriteGenres genre){
        favoriteGenres.add(genre);
    }

    public Set<FavoriteAuthors> getFavoriteAuthors() {
        return favoriteAuthors;
    }

    public FavoriteAuthors findFavoriteAuthor(Author bookAuthor){

        FavoriteAuthors foundAuthor = null;

        for (FavoriteAuthors author : favoriteAuthors) {
            if (author.getAuthor().equals(bookAuthor)) {
                foundAuthor = author;
                break;
            }
        }

        if (foundAuthor == null) {
            foundAuthor = new FavoriteAuthors(this, bookAuthor, 0);
            addFavoriteAuthor(foundAuthor);
        }

        return foundAuthor;
    }

    public void setFavoriteAuthors(Set<FavoriteAuthors> favoriteAuthors) {
        this.favoriteAuthors = favoriteAuthors;
    }

    public void addFavoriteAuthor(FavoriteAuthors author){
        favoriteAuthors.add(author);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", books=" + library +
                ", shelves=" + shelves +
                ", favorites=" + favorites +
                ", pagesReadStatistics=" + pagesReadStatistics +
                ", booksReadStatistics=" + booksReadStatistics +
                ", favoriteGenres=" + favoriteGenres +
                ", favoriteAuthors=" + favoriteAuthors +
                '}';
    }
}
