package com.nashss.se.collectorsstash.activity;

import com.amazonaws.services.dynamodbv2.model.Get;
import com.nashss.se.collectorsstash.activity.request.GetPriceComicBookRequest;
import com.nashss.se.collectorsstash.activity.results.GetPriceComicBookResult;
import com.nashss.se.collectorsstash.dynamodb.ComicBookDao;
import com.nashss.se.collectorsstash.dynamodb.models.ComicBook;
import com.nashss.se.collectorsstash.testHelper.AllComicBooksTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetPriceComicBookActivityTest {
    @Mock
    private ComicBookDao comicBookDao;

    private GetPriceComicBookActivity getPriceComicBookActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        getPriceComicBookActivity = new GetPriceComicBookActivity(comicBookDao);
    }
    @Test
    void handleRequest_test_isSuccessful() {
        List<ComicBook> comicBookList = AllComicBooksTestHelper.generateComicList(3);
        String seriesId = comicBookList.get(0).getSeriesId();
//        int price = comicBookList.get(0).getPrice();

        GetPriceComicBookRequest request = GetPriceComicBookRequest.builder()
                .withSeriesId(seriesId)
                .withPrice(51)
                .build();

        when(comicBookDao.getComicByPrice(seriesId, 51)).thenReturn(comicBookList);

        GetPriceComicBookResult result = getPriceComicBookActivity.handleRequest(request);

        assertEquals(comicBookList.size(), result.getComicList().size());
    }
}
