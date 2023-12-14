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

    /**
     * Instantiates a new CreateSeriesActivity object.
     *
     * @param seriesDao SeriesDao to access the series table.
     */
    @Inject
    public CreateSeriesActivity(SeriesDao seriesDao) {
        this.seriesDao = seriesDao;
    }
    /**
     * This method handles the incoming request by persisting a new Series
     * with the provided ComicBook customerId, title, volumeNumber from the request.
     * <p>
     * It then returns the newly created Series.
     * <p>
     *
     * @param createSeriesRequest request object containing the customerId, title, volumeNumber associated with it
     * @return createSeries result object containing the API defined {@link SeriesModel}
     */
    public CreateSeriesResults handleRequest(final CreateSeriesRequest createSeriesRequest) {
        log.info("Received CreateSeriesRequest {}", createSeriesRequest);

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
