package com.nashss.se.collectorsstash.activity;
import com.nashss.se.collectorsstash.activity.request.GetSeriesRequest;
import com.nashss.se.collectorsstash.activity.results.GetSeriesResults;
import com.nashss.se.collectorsstash.converter.ModelConverter;
import com.nashss.se.collectorsstash.dynamodb.SeriesDao;
import com.nashss.se.collectorsstash.dynamodb.models.Series;
import com.nashss.se.collectorsstash.models.SeriesModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import javax.inject.Inject;

public class GetSeriesActivity {

    private final Logger log = LogManager.getLogger();
    private final SeriesDao seriesDao;
    /**
     * Constructs a new activity for retrieving series information.
     *
     * @param seriesDao The SeriesDao used for accessing the data store.
     */
    @Inject
    public GetSeriesActivity(SeriesDao seriesDao) {
        this.seriesDao = seriesDao;
    }
    /**
     * Handles the incoming request to retrieve a list of series for a given customer.
     *
     * <p>This method processes the GetSeriesRequest, extracts the customer ID, and calls the SeriesDao
     * to fetch the relevant series. The retrieved series are then converted to SeriesModel instances
     * using a ModelConverter.</p>
     *
     * @param getSeriesRequest The request object containing the customer ID.
     * @return GetSeriesResults object containing the API-defined {@link SeriesModel} list.
     */
    public GetSeriesResults handleRequest(final GetSeriesRequest getSeriesRequest) {
        log.info("Received GetSeriesRequest {}", getSeriesRequest);

        String requestedCustomerId = getSeriesRequest.getCustomerId();
        List<Series> series = seriesDao.getSeries(requestedCustomerId);

        List<SeriesModel> seriesModel = new ModelConverter().toSeriesModelList(series);

        return GetSeriesResults.builder()
                .withSeriesList(seriesModel)
                .build();
    }
}
