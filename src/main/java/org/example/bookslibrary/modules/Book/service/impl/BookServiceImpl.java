package org.example.bookslibrary.modules.Book.service.impl;

import org.example.bookslibrary.modules.Book.dto.BookResponse;
import org.example.bookslibrary.exception.NotFoundException;
import org.example.bookslibrary.modules.Book.mapper.BookMapper;
import org.example.bookslibrary.model.Book.Book;
import org.example.bookslibrary.model.Book.BookRepository;
import org.example.bookslibrary.model.Library.Library;
import org.example.bookslibrary.model.User.User;
import org.example.bookslibrary.model.User.UserRepository;
import org.example.bookslibrary.modules.Book.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookResponse> getBooks(Pageable pageable, String userId) {
        User user = getUserEntity(userId);
        Set<Library> booksInLibrary = user.getLibrary();
        Set<Book> favoriteBooks = user.getFavorites();

        return bookRepository
                .findAll(pageable)
                .map((book) -> {
                            boolean isFavorite = favoriteBooks.contains(book);
                            boolean isBookInLibrary = isBookInLibrary(booksInLibrary, book);

                            return bookMapper.toResponse(book, isFavorite, isBookInLibrary);
                        }
                )
                .toList();
    }

    @Override
    public BookResponse getBookInfo(String bookId, String userId) {
        User user = getUserEntity(userId);
        Set<Library> booksInLibrary = user.getLibrary();
        Book book = getBookEntity(bookId);
        Set<Book> favoriteBooks = getUserEntity(userId).getFavorites();
        boolean isFavorite = favoriteBooks.contains(book);
        boolean isBookInLibrary = isBookInLibrary(booksInLibrary, book);

        return bookMapper.toResponse(book, isFavorite, isBookInLibrary);
    }

    @Override
    public long countBooks() {
        return bookRepository.count();
    }

    private boolean isBookInLibrary(Set<Library> booksInLibrary, Book book) {
        return booksInLibrary.stream().anyMatch((library -> library.getBook().equals(book)));
    }

    private User getUserEntity(String userId) {
        return userRepository.findById(userId).orElseThrow(NotFoundException::new);
    }

    private Book getBookEntity(String bookId) {
        return bookRepository.findById(bookId).orElseThrow(NotFoundException::new);
    }
}
