package com.nashss.se.collectorsstash.activity;

import com.nashss.se.collectorsstash.activity.request.GetSeriesRequest;
import com.nashss.se.collectorsstash.activity.results.GetSeriesResults;
import com.nashss.se.collectorsstash.dynamodb.SeriesDao;

import com.nashss.se.collectorsstash.dynamodb.models.Series;
import com.nashss.se.collectorsstash.models.SeriesModel;
import com.nashss.se.collectorsstash.testHelper.SeriesTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetSeriesActivityTest {
    @Mock
    private SeriesDao seriesDao;

    private GetSeriesActivity getSeriesActivity;

    @BeforeEach
    void setUp() {
        initMocks(this);
        getSeriesActivity =  new GetSeriesActivity(seriesDao);
    }

    @Test
    void handleRequest_test_isSuccessful() {

        List<Series> seriesList = SeriesTestHelper.generateSeriesList(4);
        String startCustomerId = seriesList.get(0).getCustomerId();

        GetSeriesRequest request = GetSeriesRequest.builder()
                .withCustomerId(startCustomerId)
                .build();
        // Mock the behavior of SeriesDao
        when(seriesDao.getSeries(startCustomerId)).thenReturn(seriesList);

        // Call the method being tested
        GetSeriesResults results = getSeriesActivity.handleRequest(request);

        List<SeriesModel> seriesModels = results.getSeriesList();

        // Check if the results match the expected SeriesModel list
        for(int i = 0; i <seriesModels.size(); i++){
            assertEquals(seriesList.get(i).getSeriesId(), seriesModels.get(i).getSeriesId());
        }
    }
}
