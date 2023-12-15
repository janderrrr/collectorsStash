package com.nashss.se.collectorsstash.activity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.collectorsstash.activity.request.UpdateSeriesRequest;
import com.nashss.se.collectorsstash.activity.results.UpdateSeriesResult;
import com.nashss.se.collectorsstash.dynamodb.SeriesDao;
import com.nashss.se.collectorsstash.dynamodb.models.Series;
import com.nashss.se.collectorsstash.exceptions.SeriesNotFoundException;
import com.nashss.se.collectorsstash.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateSeriesActivityTest {
    @Mock
    private SeriesDao seriesDao;
    @Mock
    private MetricsPublisher metricsPublisher;
    private UpdateSeriesActivity updateSeriesActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        updateSeriesActivity = new UpdateSeriesActivity(seriesDao, metricsPublisher);
    }
    @Test
    void handleRequest_test_seriesNotFound() {
        // Given a new request
        String customerId = "jojo@gmail.com";
        String seriesId = "x12345";
        String expectedTitle = "Amazing Spider-Man";
        String expectedVolumeNumber = "1";

        // Create an UpdateSeriesRequest object
        UpdateSeriesRequest request = UpdateSeriesRequest.builder()
                .withCustomerId(customerId)
                .withSeriesId(seriesId)
                .withTitle(expectedTitle)
                .withVolumeNumber(expectedVolumeNumber)
                .build();

        // Mock the behavior of seriesDao.getOneSeries to return null
        when(seriesDao.getOneSeries(customerId, seriesId)).thenReturn(null);

        // Assert that a SeriesNotFoundException is thrown
        assertThrows(SeriesNotFoundException.class, () -> updateSeriesActivity.handleRequest(request));
    }

    @Test
    void handleRequest_test_isSuccessful() {
        // Given a new request
        String customerId = "jojo@gmail.com";
        String seriesId = "x12345";
        String expectedTitle = "Amazing Spider-Man";
        String expectedVolumeNumber = "1";

        // Create an UpdateSeriesRequest object
        UpdateSeriesRequest request = UpdateSeriesRequest.builder()
                .withCustomerId(customerId)
                .withSeriesId(seriesId)
                .withTitle(expectedTitle)
                .withVolumeNumber(expectedVolumeNumber)
                .build();

        // Old series, aka what we start with
        Series startingSeries = new Series();
        startingSeries.setCustomerId(customerId);
        startingSeries.setSeriesId(seriesId);
        startingSeries.setTitle("Old title");
        startingSeries.setVolumeNumber("2");

        // Mock the behavior of seriesDao.getOneSeries
        when(seriesDao.getOneSeries(customerId, seriesId)).thenReturn(startingSeries);

        // Mock the behavior of seriesDao.saveSeries
        when(seriesDao.saveSeries(startingSeries)).thenReturn(startingSeries);

        // Call the method being tested
        UpdateSeriesResult result = updateSeriesActivity.handleRequest(request);

        // Check if the updated series in the result has the expected title and volumeNumber
        assertEquals(expectedTitle, result.getSeriesModel().getTitle());
        assertEquals(expectedVolumeNumber, result.getSeriesModel().getVolumeNumber());
    }
}
