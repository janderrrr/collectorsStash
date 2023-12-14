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
    /**
     * Constructs a new activity for removing a series.
     *
     * <p>This activity is responsible for handling the logic to remove a series using the provided SeriesDao.</p>
     *
     * @param seriesDao The SeriesDao used for accessing the data store.
     */
    @Inject
    public RemoveSeriesActivity(SeriesDao seriesDao) {
        this.seriesDao = seriesDao;
    }
    /**
     * Handles the incoming request to remove a series associated with a specific customer.
     *
     * <p>This method processes the RemoveSeriesRequest, extracts the customer ID and series ID,
     * and calls the SeriesDao to remove the specified series. If the provided series ID is blank,
     * an {@link IllegalArgumentException} is thrown with an appropriate error message.</p>
     *
     * @param removeSeriesRequest The request object containing the customer ID and series ID to be removed.
     * @return RemoveSeriesResult object containing the API-defined {@link SeriesModel} of the removed series.
     * @throws IllegalArgumentException If the series ID provided in the request is blank.
     */
    public RemoveSeriesResult handleRequest(final RemoveSeriesRequest removeSeriesRequest) {
        log.info("Received removeSeriesRequest {}", removeSeriesRequest);

        String seriesId = removeSeriesRequest.getSeriesId();
        String customerId = removeSeriesRequest.getCustomerId();

        if (seriesId.isBlank()) {
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
