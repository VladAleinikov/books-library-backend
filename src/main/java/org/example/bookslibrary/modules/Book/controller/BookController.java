package org.example.bookslibrary.modules.Book.controller;

import org.example.bookslibrary.modules.Book.dto.BookResponse;
import org.example.bookslibrary.security.user.AuthUser;
import org.example.bookslibrary.modules.Book.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<BookResponse> getBooks(@PageableDefault Pageable pageable, @AuthenticationPrincipal AuthUser authUser) {
        return bookService.getBooks(pageable, authUser.getId());
    }

    @GetMapping("/{bookId}")
    public BookResponse getBookInfo(@PathVariable("bookId") String bookId, @AuthenticationPrincipal AuthUser authUser) {
        return bookService.getBookInfo(bookId, authUser.getId());
    }

    @GetMapping("/count")
    public long countUsers() {
        return bookService.countBooks();
    }
}
