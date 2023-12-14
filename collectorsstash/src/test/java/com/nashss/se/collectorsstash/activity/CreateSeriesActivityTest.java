package com.nashss.se.collectorsstash.activity;

import com.nashss.se.collectorsstash.activity.request.CreateSeriesRequest;
import com.nashss.se.collectorsstash.activity.results.CreateSeriesResults;
import com.nashss.se.collectorsstash.converter.ModelConverter;
import com.nashss.se.collectorsstash.dynamodb.SeriesDao;
import com.nashss.se.collectorsstash.dynamodb.models.Series;
import com.nashss.se.collectorsstash.models.SeriesModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateSeriesActivityTest {
    @Mock
    private SeriesDao seriesDao;

    private CreateSeriesActivity createSeriesActivity;

    @BeforeEach
    void setUp(){
        openMocks(this);
        createSeriesActivity = new CreateSeriesActivity(seriesDao);
    }

    @Test
    void handleRequest_test_isSuccessful() {
        CreateSeriesRequest request = new CreateSeriesRequest("jojo@gmail.com", "ASM", "1");

        Series savedSeries = new Series();
        savedSeries.setCustomerId(request.getCustomerId());
        savedSeries.setTitle(request.getTitle());
        savedSeries.setVolumeNumber(request.getVolumeNumber());

        when(seriesDao.saveSeries(any(Series.class))).thenReturn(savedSeries);

        CreateSeriesResults results = createSeriesActivity.handleRequest(request);

        Series expectedSeries = new Series();
        expectedSeries.setCustomerId("jojo@gmail.com");
        expectedSeries.setTitle("ASM");
        expectedSeries.setVolumeNumber("1");

        SeriesModel expectedSeriesModel = new ModelConverter().toSeriesModel(expectedSeries);

        assertEquals(expectedSeriesModel, results.getSeries());
    }

}
