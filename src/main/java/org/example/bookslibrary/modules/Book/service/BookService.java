package org.example.bookslibrary.modules.Book.service;

import org.example.bookslibrary.modules.Book.dto.BookResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<BookResponse> getBooks(Pageable pageable, String userId);
    BookResponse getBookInfo(String bookId, String userId);
    long countBooks();
}
