package com.nashss.se.collectorsstash.activity;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
import com.nashss.se.collectorsstash.activity.request.GetSeriesRequest;
import com.nashss.se.collectorsstash.activity.results.GetSeriesResults;
import com.nashss.se.collectorsstash.converter.ModelConverter;
import com.nashss.se.collectorsstash.dynamodb.SeriesDao;
import com.nashss.se.collectorsstash.dynamodb.models.Series;
import com.nashss.se.collectorsstash.models.SeriesModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class GetSeriesActivity {

    private final Logger log = LogManager.getLogger();
    private final SeriesDao seriesDao;

    @Inject
    public GetSeriesActivity(SeriesDao seriesDao) {
        this.seriesDao = seriesDao;
    }

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
