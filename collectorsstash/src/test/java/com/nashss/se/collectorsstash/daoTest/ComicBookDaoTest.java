package com.nashss.se.collectorsstash.daoTest;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.nashss.se.collectorsstash.dynamodb.ComicBookDao;
import com.nashss.se.collectorsstash.dynamodb.models.ComicBook;
import com.nashss.se.collectorsstash.metrics.MetricsConstants;
import com.nashss.se.collectorsstash.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ComicBookDaoTest {
    @Mock
    private DynamoDBMapper mapper;
    @Mock
    private MetricsPublisher metricsPublisher;
    @Mock
    private QueryResultPage<ComicBook> queryResultPage;
    @InjectMocks
    private ComicBookDao comicBookDao;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }


    @Test
    public void getAllComicBooks_withPopulatedComicBooksTable_returnsListComicBooks() {
        // GIVEN
        String seriesId = "12345";

        ComicBook comicBook1 = new ComicBook();
        comicBook1.setSeriesId(seriesId);
        comicBook1.setTitle("Comic1");
        comicBook1.setVolumeNumber("1");

        ComicBook comicBook2 = new ComicBook();
        comicBook2.setSeriesId(seriesId);
        comicBook2.setTitle("Comic2");
        comicBook2.setVolumeNumber("2");

        List<ComicBook> expectedComicBookList = Arrays.asList(comicBook1, comicBook2);

        // Mock the behavior of the DynamoDB queryPage method
        when(mapper.queryPage(eq(ComicBook.class), any(DynamoDBQueryExpression.class))).thenReturn(queryResultPage);
        when(queryResultPage.getResults()).thenReturn(expectedComicBookList); // Mock the results

        // WHEN
        List<ComicBook> result = comicBookDao.getAllComicBooks(seriesId);

        // THEN
        assertEquals(expectedComicBookList, result, "Expected list of comic books to be what was returned by DynamoDB");
        verify(mapper).queryPage(eq(ComicBook.class), any(DynamoDBQueryExpression.class));
    }

    @Test
    public void testComicSave() {
        // GIVEN
        ComicBook comicBookToSave = new ComicBook();

        // WHEN
        ComicBook savedComicBook = comicBookDao.comicSave(comicBookToSave);

        // THEN
        // Verify that the save method was called with the correct argument
        verify(mapper).save(eq(comicBookToSave));

        // Verify that the returned ComicBook is the same instance as the one passed to save
        assertEquals(comicBookToSave, savedComicBook, "Expected the same instance of ComicBook to be returned");
    }

    @Test
    public void getComicByPrice_withValidData_returnsListOfComicBooks() {
        // GIVEN
        String seriesId = "12345";
        int price = 50; // adjust the price as needed

        ComicBook comicBook1 = new ComicBook();
        comicBook1.setSeriesId(seriesId);
        comicBook1.setTitle("Comic1");
        comicBook1.setVolumeNumber("1");

        ComicBook comicBook2 = new ComicBook();
        comicBook2.setSeriesId(seriesId);
        comicBook2.setTitle("Comic2");
        comicBook2.setVolumeNumber("2");

        List<ComicBook> expectedComicBookList = Arrays.asList(comicBook1, comicBook2);

        when(mapper.queryPage(eq(ComicBook.class), any(DynamoDBQueryExpression.class))).thenReturn(queryResultPage);
        when(queryResultPage.getResults()).thenReturn(expectedComicBookList);

        // WHEN
        List<ComicBook> result = comicBookDao.getComicByPrice(seriesId, price);

        // THEN
        assertEquals(expectedComicBookList, result, "Expected list of comic books to be what was returned by DynamoDB");
        verify(mapper).queryPage(eq(ComicBook.class), any(DynamoDBQueryExpression.class));
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETALLCOMICBOOKS_FAIL_COUNT), eq(0.0d));
    }



}
