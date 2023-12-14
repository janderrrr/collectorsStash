package com.nashss.se.collectorsstash.converter;

import com.nashss.se.collectorsstash.dynamodb.models.ComicBook;
import com.nashss.se.collectorsstash.dynamodb.models.Series;
import com.nashss.se.collectorsstash.models.ComicBookModel;
import com.nashss.se.collectorsstash.models.SeriesModel;

import java.util.Comparator;
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
                .withSeriesId(comicBook.getSeriesId())
                .withCustomerId(comicBook.getCustomerId())
                .withTitle(comicBook.getTitle())
                .withVolumeNumber(comicBook.getVolumeNumber())
                .withIssueNumber(comicBook.getIssueNumber())
                .withYear(comicBook.getYear())
                .withPrice(comicBook.getPrice())
                .withIsFavorite(comicBook.isFavorite())
                .withPublisher(comicBook.getPublisher())
                .build();
    }

    public List<SeriesModel> toSeriesModelList(List<Series> series) {
        return series.stream()
                .map(this::toSeriesModel)
                .collect(Collectors.toList());
    }

    public SeriesModel toSeriesModel(Series series) {
        if (series == null) {
            return null;
        }

        return SeriesModel.builder()
                .withCustomerId(series.getCustomerId())
                .withSeriesId(series.getSeriesId())
                .withTitle(series.getTitle())
                .withVolumeNumber(series.getVolumeNumber())
                .build();
    }
}
