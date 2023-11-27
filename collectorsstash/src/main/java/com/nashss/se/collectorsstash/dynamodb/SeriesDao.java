package com.nashss.se.collectorsstash.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.nashss.se.collectorsstash.dynamodb.models.Series;
import com.nashss.se.collectorsstash.exceptions.SeriesNotFoundException;
import com.nashss.se.collectorsstash.metrics.MetricsConstants;
import com.nashss.se.collectorsstash.metrics.MetricsPublisher;

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

        List<Series> seriesList = mapper.query(Series.class, queryExpression);

         if(seriesList.isEmpty()) {
            metricsPublisher.addCount(MetricsConstants.GETSERIES_FAIL_COUNT, 1);
            throw new SeriesNotFoundException("Series Not Found");
        }
        metricsPublisher.addCount(MetricsConstants.GETSERIES_SUCCESS_COUNT, 1);
        return seriesList;
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
}
