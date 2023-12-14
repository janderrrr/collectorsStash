package com.nashss.se.collectorsstash.activity;

import com.nashss.se.collectorsstash.activity.request.UpdateSeriesRequest;
import com.nashss.se.collectorsstash.activity.results.UpdateSeriesResult;
import com.nashss.se.collectorsstash.converter.ModelConverter;
import com.nashss.se.collectorsstash.dynamodb.SeriesDao;
import com.nashss.se.collectorsstash.dynamodb.models.Series;
import com.nashss.se.collectorsstash.exceptions.SeriesNotFoundException;
import com.nashss.se.collectorsstash.metrics.MetricsPublisher;
import com.nashss.se.collectorsstash.models.SeriesModel;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateSeriesActivity {
    private final Logger log = LogManager.getLogger();
    private final SeriesDao seriesDao;
    private final MetricsPublisher metricsPublisher;
    /**
     * Constructs a new activity for updating series information.
     *
     * @param seriesDao        The SeriesDao used for accessing the data store.
     * @param metricsPublisher The MetricsPublisher used for tracking metrics.
     */
    @Inject
    public UpdateSeriesActivity(SeriesDao seriesDao, MetricsPublisher metricsPublisher) {
        this.seriesDao = seriesDao;
        this.metricsPublisher = metricsPublisher;
    }
    /**
     * Handles the incoming request to update information for a specific series.
     *
     * <p>This method processes the UpdateSeriesRequest, retrieves the existing series from the SeriesDao,
     * and performs the necessary updates on the series object. It then calls the SeriesDao to persist
     * the changes and returns the updated series information as a SeriesModel.</p>
     *
     * <p>If the series is not found in the data store, a {@link SeriesNotFoundException} is thrown,
     * indicating that the series does not exist for an update. Additionally, if the series ID provided
     * in the request does not match the series ID of the retrieved series, another
     * {@link SeriesNotFoundException} is thrown to ensure that the series being updated exists.</p>
     *
     * @param updateSeriesRequest The request object containing the customer ID, series ID, and updated information.
     * @return UpdateSeriesResult object containing the API-defined {@link SeriesModel} of the updated series.
     * @throws SeriesNotFoundException If the series is not found for update or if the series ID mismatch occurs.
     */
    public UpdateSeriesResult handleRequest(final UpdateSeriesRequest updateSeriesRequest) {
        log.info("Received UpdateSeriesRequest {}", updateSeriesRequest);

        Series series = seriesDao.getOneSeries(
                updateSeriesRequest.getCustomerId(),
                updateSeriesRequest.getSeriesId()
        );

        if (series == null) {
            throw new SeriesNotFoundException("Series not found for update");
        }
        if (!series.getSeriesId().equals(updateSeriesRequest.getSeriesId())) {
            throw new SeriesNotFoundException("Series must exist to update");
        }


        series.setTitle(updateSeriesRequest.getTitle());
        series.setVolumeNumber(updateSeriesRequest.getVolumeNumber());

        series = seriesDao.saveSeries(series);

        SeriesModel seriesModel = new ModelConverter().toSeriesModel(series);

        return UpdateSeriesResult.builder()
                .withSeriesModel(seriesModel)
                .build();
    }
}
