package com.nashss.se.collectorsstash.modelConvertor;

import com.nashss.se.collectorsstash.converter.ModelConverter;
import com.nashss.se.collectorsstash.dynamodb.models.ComicBook;
import com.nashss.se.collectorsstash.dynamodb.models.Series;
import com.nashss.se.collectorsstash.models.ComicBookModel;
import com.nashss.se.collectorsstash.models.SeriesModel;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelConvertorTest {
    private ModelConverter modelConverter = new ModelConverter();

    @Test
    void toComicModel_withSingleComic_toComicModel() {
        //Given
        ComicBook comicBook = new ComicBook();
        comicBook.setCustomerId("jojo@gmail.com");
        comicBook.setSeriesId("x12345");
        comicBook.setTitle("title");
        comicBook.setVolumeNumber("1");
        comicBook.setIssueNumber("1");
        comicBook.setYear("1998");
        comicBook.setFavorite(true);
        comicBook.setPublisher("Marvel");
        comicBook.setPrice(50);

        ComicBookModel result = modelConverter.toComicModel(comicBook);

        assertEquals(comicBook.getCustomerId(), result.getCustomerId());
        assertEquals(comicBook.getTitle(), result.getTitle());
    }

    @Test
    void toComicModelList_withComicList_ToComicList() {
        //Given
        ComicBook comicBook = new ComicBook();
        comicBook.setCustomerId("jojo@gmail.com");
        comicBook.setSeriesId("x12345");
        comicBook.setTitle("title");
        comicBook.setVolumeNumber("1");
        comicBook.setIssueNumber("1");
        comicBook.setYear("1998");
        comicBook.setFavorite(true);
        comicBook.setPublisher("Marvel");
        comicBook.setPrice(50);
        ComicBook comicBook2 = new ComicBook();
        comicBook2.setCustomerId("jojo@gmail.com");
        comicBook2.setSeriesId("x12345");
        comicBook2.setTitle("title");
        comicBook2.setVolumeNumber("1");
        comicBook2.setIssueNumber("1");
        comicBook2.setYear("1998");
        comicBook2.setFavorite(true);
        comicBook2.setPublisher("Marvel");
        comicBook2.setPrice(50);

        List<ComicBook> comicBookList = Arrays.asList(comicBook, comicBook2);

        //when
        List<ComicBookModel> result = modelConverter.toComicBookModelList(comicBookList);

        assertEquals(comicBookList.size(), result.size());
    }


    @Test
    void toSeriesModel_withSingleSeries_toSeriesModel() {
        Series series = new Series();
        series.setCustomerId("jojo@gmail.com");
        series.setSeriesId("x12345");
        series.setTitle("title");
        series.setVolumeNumber("2");

        SeriesModel result = modelConverter.toSeriesModel(series);

        assertEquals(series.getCustomerId(), result.getCustomerId());
        assertEquals(series.getSeriesId(), result.getSeriesId());
    }

    @Test
    void toSeriesModelList_withSeriesList_toSeriesList() {
        Series series = new Series();
        series.setCustomerId("jojo@gmail.com");
        series.setSeriesId("x12345");
        series.setTitle("title");
        series.setVolumeNumber("2");

        Series series2 = new Series();
        series2.setCustomerId("jojo@gmail.com");
        series2.setSeriesId("x12345");
        series2.setTitle("title");
        series2.setVolumeNumber("2");
        List<Series> seriesList = Arrays.asList(series, series2);

        //when
        List<SeriesModel> result = modelConverter.toSeriesModelList(seriesList);

        assertEquals(seriesList.size(), result.size());
    }
}
