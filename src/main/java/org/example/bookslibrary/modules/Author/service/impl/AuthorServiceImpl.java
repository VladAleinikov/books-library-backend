package org.example.bookslibrary.modules.Author.service.impl;

import org.example.bookslibrary.exception.NotFoundException;
import org.example.bookslibrary.model.Author.Author;
import org.example.bookslibrary.model.Author.AuthorRepository;
import org.example.bookslibrary.modules.Author.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAuthors(String searchQuery) {
        return authorRepository.findAuthorsByNameContaining(searchQuery);
    }

    @Override
    public Author getAuthor(String authorId) {
        return getAuthorEntity(authorId);
    }

    @Override
    public Author createAuthor(String name) {
        Author author = new Author(name);
        return authorRepository.save(author);
    }

    private Author getAuthorEntity(String authorId) {
        return authorRepository.findById(authorId).orElseThrow(NotFoundException::new);
    }
}
