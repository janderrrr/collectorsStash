package com.nashss.se.collectosstash.converter;

import com.nashss.se.collectosstash.dynamodb.models.ComicBook;
import com.nashss.se.collectosstash.models.ComicBookModel;

import java.util.List;
import java.util.stream.Collectors;

public class ModelConverter {

    public List<ComicBookModel> toComicBookModelList(List<ComicBook> comicBooks) {
        return comicBooks.stream()
                .map(this::toComicModel)
                .collect(Collectors.toList());
    }
    /**
     * Converts a provided comic book into a ComicBookModel representation.
     *
     * @param comicBook the ComicBook to convert to ComicBookModel
     * @return the converted ComicBookModel with fields mapped from ComicBook
     */
    public ComicBookModel toComicModel(ComicBook comicBook) {
        return ComicBookModel.builder()
                .withSeriesTitle(comicBook.getSeriesTitle())
                .withVolumeNumber(comicBook.getVolumeNumber())
                .withIssueNumber(comicBook.getIssueNumber())
                .withDate(comicBook.getDate())
                .withPrice(comicBook.getPrice())
                .withIsFavorite(comicBook.isFavorite())
                .withPublisher(comicBook.getPublisher())
                .build();
    }
}
