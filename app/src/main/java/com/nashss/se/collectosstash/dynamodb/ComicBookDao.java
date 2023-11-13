package com.nashss.se.collectosstash.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.collectosstash.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ComicBookDao {

    private final MetricsPublisher metricsPublisher;
    private final DynamoDBMapper mapper;

    @Inject
    private ComicBookDao(DynamoDBMapper mapper, MetricsPublisher metricsPublisher) {
        this.mapper = mapper;
        this.metricsPublisher = metricsPublisher;
    }

}
