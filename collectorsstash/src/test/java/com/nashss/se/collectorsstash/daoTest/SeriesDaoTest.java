package com.nashss.se.collectorsstash.daoTest;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.nashss.se.collectorsstash.dynamodb.SeriesDao;
import com.nashss.se.collectorsstash.dynamodb.models.Series;
import com.nashss.se.collectorsstash.exceptions.SeriesNotFoundException;
import com.nashss.se.collectorsstash.metrics.MetricsConstants;
import com.nashss.se.collectorsstash.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class SeriesDaoTest {
    @Mock
    private DynamoDBMapper mapper;
    @Mock
    private MetricsPublisher metricsPublisher;
    @Mock
    private QueryResultPage<Series> queryResultPage;
    @InjectMocks
    private SeriesDao seriesDao;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void getSeries_withPopulatedSeriesTable_returnsSeriesList() {
        // GIVEN
        String customerId = "jojo@gmail.com";
        Series series = new Series();
        series.setCustomerId(customerId);

        when(mapper.queryPage(eq(Series.class), any(DynamoDBQueryExpression.class)))
                .thenReturn(queryResultPage);

        // Ensure that queryResultPage is properly initialized
        when(queryResultPage.getResults()).thenReturn(Collections.singletonList(series));

        // WHEN
        var result = seriesDao.getSeries(customerId);

        // THEN
        assertEquals(Collections.singletonList(series), result, "Expected list of series to be what was returned");
        verify(mapper).queryPage(eq(Series.class), any(DynamoDBQueryExpression.class));
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETSERIES_FAIL_COUNT), eq(1.0d));
    }
    @Test
    void getOneSeries_withValidData_returnsSeries() {
        // GIVEN
        String customerId = "jojo@gmail.com";
        String seriesId = "12345";

        Series expectedSeries = new Series();
        expectedSeries.setCustomerId(customerId);
        expectedSeries.setSeriesId(seriesId);
        expectedSeries.setTitle("title");
        expectedSeries.setVolumeNumber("1");

        when(mapper.load(Series.class, customerId, seriesId)).thenReturn(expectedSeries);

        // WHEN
        Series result = seriesDao.getOneSeries(customerId, seriesId);

        // THEN
        assertEquals(expectedSeries, result, "Expected series to be what was returned");
        verify(metricsPublisher).addCount(MetricsConstants.GETSERIES_SUCCESS_COUNT, 1);
    }
    @Test
    void getOneSeries_withInvalidData_throwsSeriesNotFoundException() {
        // GIVEN
        String customerId = "jojo@gmail.com";
        String seriesId = "invalidSeriesId";

        when(mapper.load(Series.class, customerId, seriesId)).thenReturn(null);

        // WHEN and THEN
        assertThrows(SeriesNotFoundException.class, () -> seriesDao.getOneSeries(customerId, seriesId));
        verify(metricsPublisher).addCount(MetricsConstants.GETSERIES_FAIL_COUNT, 1);
    }

    @Test
    void removeSeries_withValidData_deletesSeriesAndReturnsDeletedSeries() {
        // GIVEN
        String customerId = "jojo@gmail.com";
        String seriesId = "12345";

        Series seriesToDelete = new Series();
        seriesToDelete.setCustomerId(customerId);
        seriesToDelete.setSeriesId(seriesId);

        // WHEN
        Series result = seriesDao.removeSeries(customerId, seriesId);

        // THEN
        assertEquals(seriesToDelete, result, "Expected removed series to be returned");
        verify(mapper).delete(eq(seriesToDelete));
    }

    @Test
    void saveSeries_withValidData_savesSeriesAndReturnsSavedSeries() {
        // GIVEN
        Series seriesToSave = new Series();
        seriesToSave.setCustomerId("jojo@gmail.com");
        seriesToSave.setTitle("My Series");
        seriesToSave.setVolumeNumber("1");

        // WHEN
        Series result = seriesDao.saveSeries(seriesToSave);

        // THEN
        assertEquals(seriesToSave, result, "Expected saved series to be returned");
        verify(mapper).save(eq(seriesToSave));
    }
}
