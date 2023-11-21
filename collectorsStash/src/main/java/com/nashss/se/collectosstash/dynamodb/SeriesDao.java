package com.nashss.se.collectosstash.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.collectosstash.dynamodb.models.Series;
import com.nashss.se.collectosstash.exceptions.SeriesNotFoundException;
import com.nashss.se.collectosstash.metrics.MetricsConstants;
import com.nashss.se.collectosstash.metrics.MetricsPublisher;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
