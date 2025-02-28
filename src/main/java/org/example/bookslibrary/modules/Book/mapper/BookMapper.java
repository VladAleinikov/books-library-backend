package org.example.bookslibrary.modules.Book.mapper;

import org.example.bookslibrary.modules.Book.dto.BookResponse;
import org.example.bookslibrary.model.Book.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponse toResponse(Book book, boolean isFavorite, boolean isInLibrary);
}
