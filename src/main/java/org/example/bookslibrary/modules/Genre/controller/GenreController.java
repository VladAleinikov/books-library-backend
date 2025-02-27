package org.example.bookslibrary.modules.Genre.controller;

import org.example.bookslibrary.model.Genre.Genre;
import org.example.bookslibrary.modules.Genre.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genre")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping()
    public List<Genre> getGenres(@RequestParam(defaultValue = "") String searchQuery){
        return genreService.getGenres(searchQuery);
    }

    @GetMapping("/{genreId}")
    public Genre getGenre(@PathVariable("genreId") String genreId){
        return genreService.getGenre(genreId);
    }

    @PostMapping()
    public Genre createGenre(@RequestBody String name){
        return genreService.createGenre(name);
    }
}
