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

    @Inject
    public UpdateSeriesActivity(SeriesDao seriesDao, MetricsPublisher metricsPublisher) {
        this.seriesDao = seriesDao;
        this.metricsPublisher = metricsPublisher;
    }

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
