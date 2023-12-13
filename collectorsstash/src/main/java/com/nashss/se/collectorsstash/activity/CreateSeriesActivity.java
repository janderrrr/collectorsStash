package com.nashss.se.collectorsstash.activity;

import com.nashss.se.collectorsstash.activity.request.CreateSeriesRequest;
import com.nashss.se.collectorsstash.activity.results.CreateSeriesResults;
import com.nashss.se.collectorsstash.converter.ModelConverter;
import com.nashss.se.collectorsstash.dynamodb.SeriesDao;
import com.nashss.se.collectorsstash.dynamodb.models.Series;
import com.nashss.se.collectorsstash.models.SeriesModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateSeriesActivity {
    private final Logger log = LogManager.getLogger();
    private final SeriesDao seriesDao;

    @Inject
    public CreateSeriesActivity(SeriesDao seriesDao) {
        this.seriesDao = seriesDao;
    }

    public CreateSeriesResults handleRequest(final CreateSeriesRequest createSeriesRequest) {
        log.info("Received CreateSeriesRequest {}", createSeriesRequest);
//
//        // Check if the series already exists
//        if (seriesDao.seriesExist(createSeriesRequest.getCustomerId(), createSeriesRequest.getTitle(), createSeriesRequest.getVolumeNumber())) {
//            // Series with the same title and volume number already exists
//            log.warn("Series with the same title and volume number already exists.");
//            // You might want to throw an exception or handle this situation accordingly
//            // For simplicity, let's return a result indicating failure
//            return CreateSeriesResults.builder()
//                    .build();
//        }

        Series series = new Series();
        series.setCustomerId(createSeriesRequest.getCustomerId());
        series.setSeriesId(seriesDao.generateSeriesId());
        series.setTitle(createSeriesRequest.getTitle());
        series.setVolumeNumber(createSeriesRequest.getVolumeNumber());
        //add checks


        seriesDao.saveSeries(series);

        SeriesModel seriesModel = new ModelConverter().toSeriesModel(series);
        return CreateSeriesResults.builder()
                .withSeries(seriesModel)
                .build();
    }
}
