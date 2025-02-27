package org.example.bookslibrary.modules.Author.controller;

import org.example.bookslibrary.model.Author.Author;
import org.example.bookslibrary.modules.Author.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAuthors(@RequestParam(defaultValue = "") String searchQuery){
        return authorService.getAuthors(searchQuery);
    }

    @GetMapping("/{authorId}")
    public Author getAuthor(@PathVariable("authorId") String authorId){
        return authorService.getAuthor(authorId);
    }

    @PostMapping
    public Author createAuthor(@RequestBody String name){
        return authorService.createAuthor(name);
    }
}
