package com.nashss.se.collectosstash.activity;

import com.nashss.se.collectosstash.activity.request.GetSeriesRequest;
import com.nashss.se.collectosstash.activity.results.GetSeriesResults;
import com.nashss.se.collectosstash.converter.ModelConverter;
import com.nashss.se.collectosstash.dynamodb.SeriesDao;
import com.nashss.se.collectosstash.dynamodb.models.Series;
import com.nashss.se.collectosstash.models.SeriesModel;
import org.apache.logging.log4j.LogManager;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

public class GetSeriesActivity {

    private final Logger log = (Logger) LogManager.getLogger();
    private final SeriesDao seriesDao;

    @Inject
    public GetSeriesActivity(SeriesDao seriesDao) {
        this.seriesDao = seriesDao;
    }

    public GetSeriesResults handleRequest(final GetSeriesRequest getSeriesRequest) {
        log.info("Received GetSeriesRequest {}");

        String requestedCustomerId = getSeriesRequest.getCustomerId();
        List<Series> series = seriesDao.getSeries(requestedCustomerId);

        List<SeriesModel> seriesModel = new ModelConverter().toSeriesModelList(series);

        return GetSeriesResults.builder()
                .withSeriesList(seriesModel)
                .build();
    }
}
