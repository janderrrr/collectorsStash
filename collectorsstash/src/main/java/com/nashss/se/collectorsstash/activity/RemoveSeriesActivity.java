package com.nashss.se.collectorsstash.activity;

import com.nashss.se.collectorsstash.activity.request.RemoveSeriesRequest;
import com.nashss.se.collectorsstash.activity.results.RemoveSeriesResult;
import com.nashss.se.collectorsstash.converter.ModelConverter;
import com.nashss.se.collectorsstash.dynamodb.SeriesDao;
import com.nashss.se.collectorsstash.dynamodb.models.Series;
import com.nashss.se.collectorsstash.models.SeriesModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class RemoveSeriesActivity {
    private final Logger log = LogManager.getLogger();
    private final SeriesDao seriesDao;

    @Inject
    public RemoveSeriesActivity(SeriesDao seriesDao) {
        this.seriesDao = seriesDao;
    }

    public RemoveSeriesResult handleRequest(final RemoveSeriesRequest removeSeriesRequest) {
        log.info("Received removeSeriesRequest {}", removeSeriesRequest);

        String seriesId = removeSeriesRequest.getSeriesId();
        String customerId = removeSeriesRequest.getCustomerId();

        if(seriesId.isBlank()) {
            throw new IllegalArgumentException("The series cannot be blank");
        }
        Series removedSeries = new Series();
        removedSeries.setCustomerId(customerId);
        removedSeries.setSeriesId(seriesId);
        seriesDao.removeSeries(customerId, seriesId);

        SeriesModel seriesModel = new ModelConverter().toSeriesModel(removedSeries);

        return RemoveSeriesResult.builder()
                .withSeriesModel(seriesModel)
                .build();
    }

}
