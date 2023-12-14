package com.nashss.se.collectorsstash.activity;

import com.nashss.se.collectorsstash.activity.request.CreateComicBookRequest;
import com.nashss.se.collectorsstash.activity.results.CreateComicBookResult;
import com.nashss.se.collectorsstash.converter.ModelConverter;
import com.nashss.se.collectorsstash.dynamodb.ComicBookDao;
import com.nashss.se.collectorsstash.dynamodb.models.ComicBook;
import com.nashss.se.collectorsstash.models.ComicBookModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateComicBookActivityTest {
    @Mock
    private ComicBookDao comicBookDao;

    private CreateComicBookActivity createComicBookActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        createComicBookActivity = new CreateComicBookActivity(comicBookDao);
    }

    @Test
    void handleRequest_test_isSuccessful() {
        CreateComicBookRequest request = new CreateComicBookRequest("jojo@gmail.com", "x12345", "ASM", "1"
        , "2", true, 25000, "Marvel", "1964");

        ComicBook savedComicBook = new ComicBook();
        savedComicBook.setSeriesId(request.getSeriesId());
        savedComicBook.setCustomerId(request.getCustomerId());
        savedComicBook.setPrice(request.getPrice());
        savedComicBook.setYear(request.getYear());
        savedComicBook.setPublisher(request.getPublisher());
        savedComicBook.setFavorite(request.getFavorite());
        savedComicBook.setTitle(request.getTitle());
        savedComicBook.setVolumeNumber(request.getVolumeNumber());
        savedComicBook.setIssueNumber(request.getIssueNumber());

        when(comicBookDao.comicSave(any(ComicBook.class))).thenReturn(savedComicBook);

        CreateComicBookResult result = createComicBookActivity.handleRequest(request);

        ComicBook expectedComic = new ComicBook();
        expectedComic.setCustomerId("jojo@gmail.com");
        expectedComic.setSeriesId("x12345");
        expectedComic.setTitle("ASM");
        expectedComic.setIssueNumber("1");
        expectedComic.setVolumeNumber("2");
        expectedComic.setFavorite(true);
        expectedComic.setPrice(25000);
        expectedComic.setPublisher("Marvel");
        expectedComic.setYear("1964");

        ComicBookModel expectedComicBookModel = new ModelConverter().toComicModel(expectedComic);

        assertEquals(expectedComicBookModel, result.getComicBook());

    }
}
