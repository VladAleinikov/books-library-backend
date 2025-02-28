package org.example.bookslibrary.modules.Book.dto;

import org.example.bookslibrary.model.Author.Author;
import org.example.bookslibrary.model.Genre.Genre;

import java.util.List;

public record BookResponse(
        String id,
        String title,
        Author author,
        List<Genre> genres,
        String description,
        String coverImgUrl,
        float avgRating,
        boolean isFavorite,
        boolean isInLibrary
) {
}
