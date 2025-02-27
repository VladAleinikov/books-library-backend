package org.example.bookslibrary.modules.Genre.service.impl;

import org.example.bookslibrary.exception.NotFoundException;
import org.example.bookslibrary.model.Genre.Genre;
import org.example.bookslibrary.model.Genre.GenreRepository;
import org.example.bookslibrary.modules.Genre.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getGenres(String searchQuery) {
        return genreRepository.findGenresByNameContaining(searchQuery);
    }

    @Override
    public Genre getGenre(String genreId) {
        return getGenreEntity(genreId);
    }

    @Override
    public Genre createGenre(String name) {
        Genre genre = new Genre(name);
        return genreRepository.save(genre);
    }

    private Genre getGenreEntity(String genreId){
        return genreRepository.findById(genreId).orElseThrow(NotFoundException::new);
    }
}
