package org.example.bookslibrary.modules.Author.service;

import org.example.bookslibrary.model.Author.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors(String searchQuery);
    Author getAuthor(String authorId);
    Author createAuthor(String name);
}
