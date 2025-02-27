package org.example.bookslibrary.modules.Genre.service;

import org.example.bookslibrary.model.Genre.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getGenres(String searchQuery);
    Genre getGenre(String genreId);
    Genre createGenre(String name);
}
