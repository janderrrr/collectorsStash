package com.nashss.se.collectorsstash.converter;

import com.nashss.se.collectorsstash.dynamodb.models.ComicBook;
import com.nashss.se.collectorsstash.dynamodb.models.Series;
import com.nashss.se.collectorsstash.models.ComicBookModel;
import com.nashss.se.collectorsstash.models.SeriesModel;

import java.util.List;
import java.util.stream.Collectors;

public class ModelConverter {
    /**
     * Converts a list of {@link ComicBook} objects to a list of {@link ComicBookModel} objects.
     *
     * <p>This method takes a list of ComicBook objects and applies the {@link #toComicModel(ComicBook)}
     * transformation to each element, producing a list of ComicBookModel objects.</p>
     *
     * @param comicBooks The list of {@link ComicBook} objects to be converted.
     * @return A list of {@link ComicBookModel} objects representing the converted comic books.
     * @see #toComicModel(ComicBook)
     */
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
    /**
     * Converts a list of {@link Series} objects to a list of {@link SeriesModel} objects.
     *
     * @param series The list of {@link Series} objects to be converted.
     * @return A list of {@link SeriesModel} objects representing the converted series.
     * @see #toSeriesModel(Series)
     */
    public List<SeriesModel> toSeriesModelList(List<Series> series) {
        return series.stream()
                .map(this::toSeriesModel)
                .collect(Collectors.toList());
    }
    /**
     * Converts a {@link Series} object to a {@link SeriesModel} object.
     *
     * @param series The {@link Series} object to be converted.
     * @return A {@link SeriesModel} object representing the converted series,
     *         or {@code null} if the input series is {@code null}.
     */
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
