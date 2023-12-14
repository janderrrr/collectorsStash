package com.nashss.se.collectorsstash.activity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.collectorsstash.activity.request.RemoveSeriesRequest;
import com.nashss.se.collectorsstash.activity.results.RemoveSeriesResult;
import com.nashss.se.collectorsstash.dynamodb.SeriesDao;
import com.nashss.se.collectorsstash.dynamodb.models.Series;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class RemoveSeriesActivityTest {
    @Mock
    private SeriesDao seriesDao;
    @Mock
    private DynamoDBMapper mapper;
    private RemoveSeriesActivity removeSeriesActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        removeSeriesActivity = new RemoveSeriesActivity(seriesDao);
    }

    @Test
    void handleRequest_test_isSuccessful() {
        // Given a new request
        String customerId = "jojo@gmail.com";
        String seriesId = "x12345";

        // Create a RemoveSeriesRequest object
        RemoveSeriesRequest request = new RemoveSeriesRequest(customerId, seriesId);

        // Mock the behavior of seriesDao.removeSeries
        // In this case, assume that no exception is thrown during deletion, indicating success
        doNothing().when(mapper).delete(any(Series.class));

        // Call the method being tested
        RemoveSeriesResult result = removeSeriesActivity.handleRequest(request);

        // Check if the seriesId and customerId in the result match the input request
        assertEquals(seriesId, result.getSeriesModel().getSeriesId());
        assertEquals(customerId, result.getSeriesModel().getCustomerId());
    }
}
