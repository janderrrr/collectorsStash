package com.nashss.se.collectorsstash.activity;

import com.nashss.se.collectorsstash.activity.request.GetSeriesRequest;
import com.nashss.se.collectorsstash.activity.results.GetSeriesResults;
import com.nashss.se.collectorsstash.dynamodb.SeriesDao;

import com.nashss.se.collectorsstash.dynamodb.models.Series;
import com.nashss.se.collectorsstash.models.SeriesModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetSeriesActivityTest {
    @Mock
    private SeriesDao seriesDao;

    private GetSeriesActivity getSeriesActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        getSeriesActivity =  new GetSeriesActivity(seriesDao);
    }

    @Test
    void handleRequest_test_isSuccessful() {
        GetSeriesRequest request = new GetSeriesRequest("jojo@gmail.com");

        // Create Series objects with unique seriesId values
        Series series1 = new Series();
        series1.setCustomerId("jojo@gmail.com");
        series1.setSeriesId("x12345");  // Make sure seriesId is unique
        series1.setTitle("ASM");
        series1.setVolumeNumber("1");

        Series series2 = new Series();
        series2.setCustomerId("jojo@gmail.com");
        series2.setSeriesId("x12346");  // Make sure seriesId is unique
        series2.setTitle("ASM");
        series2.setVolumeNumber("2");

        // Set up the expected SeriesModel objects
        List<SeriesModel> expectedSeriesModel = Arrays.asList(
                new SeriesModel("jojo@gmail.com", "x12345", "ASM", "1"),
                new SeriesModel("jojo@gmail.com", "x12346", "ASM", "2")
        );

        // Mock the behavior of SeriesDao
        when(seriesDao.getSeries("jojo@gmail.com")).thenReturn(Arrays.asList(series1, series2));

        // Call the method being tested
        GetSeriesResults results = getSeriesActivity.handleRequest(request);

        // Check if the results match the expected SeriesModel list
        assertEquals(expectedSeriesModel, results.getSeriesList());
    }
}
