package org.example.bookslibrary.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, String> {
    List<Author> findAuthorsByNameContaining(String name);
}
