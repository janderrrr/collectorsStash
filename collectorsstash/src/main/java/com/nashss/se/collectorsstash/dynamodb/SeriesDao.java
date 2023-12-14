package com.nashss.se.collectorsstash.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.nashss.se.collectorsstash.dynamodb.models.Series;
import com.nashss.se.collectorsstash.exceptions.SeriesNotFoundException;
import com.nashss.se.collectorsstash.metrics.MetricsConstants;
import com.nashss.se.collectorsstash.metrics.MetricsPublisher;
import org.apache.commons.lang3.RandomStringUtils;

import javax.inject.Inject;
import java.util.List;

public class SeriesDao {

    private static final int PAGINATION_LIMIT = 5;

    private final MetricsPublisher metricsPublisher;
    private final DynamoDBMapper mapper;

    @Inject
    public SeriesDao(DynamoDBMapper mapper, MetricsPublisher metricsPublisher){
        this.mapper = mapper;
        this.metricsPublisher = metricsPublisher;
    }

    public List<Series> getSeries(String customerId){
        Series series = new Series();
        series.setCustomerId(customerId);


        DynamoDBQueryExpression<Series> queryExpression = new DynamoDBQueryExpression<Series>()
                .withHashKeyValues(series);

        QueryResultPage<Series> seriesList = mapper.queryPage(Series.class, queryExpression);


         if(seriesList == null) {
            metricsPublisher.addCount(MetricsConstants.GETSERIES_FAIL_COUNT, 1);
            throw new SeriesNotFoundException("Series Not Found");
        }
        metricsPublisher.addCount(MetricsConstants.GETSERIES_SUCCESS_COUNT, 1);
        return seriesList.getResults();
    }

    public Series getOneSeries(String customerId, String seriesId) {
        Series series = this.mapper.load(Series.class, customerId, seriesId);

        if(series == null) {
            metricsPublisher.addCount(MetricsConstants.GETSERIES_FAIL_COUNT, 1);
            throw new SeriesNotFoundException("Could not find series " + seriesId);
        } else {
            metricsPublisher.addCount(MetricsConstants.GETSERIES_SUCCESS_COUNT, 1);
            return series;
        }

    }

    public Series removeSeries(String customerId, String seriesId) {
        if(seriesId == null || customerId == null) {
            throw new IllegalArgumentException("series cannot be null");
        }
        Series seriesToDelete = new Series();
        seriesToDelete.setCustomerId(customerId);
        seriesToDelete.setSeriesId(seriesId);
        try {
            mapper.delete(seriesToDelete);
        } catch (DynamoDBMappingException e) {
            throw new RuntimeException("Error deleting Series item", e);
        }
        return seriesToDelete;
    }
    /**
     * Saves (creates or updates) the given series.
     *
     * @param series The series to save
     * @return The Series object that was saved
     */
    public Series saveSeries(Series series) {
        this.mapper.save(series);
        return series;
    }

//    public boolean seriesExist(String customerId, String title, String volumeNumber) {
//        Series series = new Series();
//        series.setCustomerId(customerId);
//        series.setTitle(title);
//        series.setVolumeNumber(volumeNumber);
//
//        DynamoDBQueryExpression<Series> queryExpression = new DynamoDBQueryExpression<Series>()
//                .withHashKeyValues(series);
//
//        List<Series> seriesList = mapper.query(Series.class, queryExpression);
//        return !seriesList.isEmpty();
//    }
    //Creating a SeriesId random UUID, to be unique when creating each Series
    public String generateSeriesId(){
        return RandomStringUtils.randomAlphabetic(6);
    }
}
