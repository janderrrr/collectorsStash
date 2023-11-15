package com.nashss.se.collectosstash.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.collectosstash.dynamodb.models.ComicBook;
import com.nashss.se.collectosstash.exceptions.ComicBookNotFoundException;
import com.nashss.se.collectosstash.metrics.MetricsConstants;
import com.nashss.se.collectosstash.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class ComicBookDao {

    private static final int PAGINATION_LIMIT = 5;
    private final MetricsPublisher metricsPublisher;
    private final DynamoDBMapper mapper;

    @Inject
    private ComicBookDao(DynamoDBMapper mapper, MetricsPublisher metricsPublisher) {
        this.mapper = mapper;
        this.metricsPublisher = metricsPublisher;
    }

    public List<ComicBook> getAllComicBooks(String seriesTitle, String volumeNumber){
        Map<String, AttributeValue> valueMap = new HashMap<>();
        if(seriesTitle != null && volumeNumber != null) {
            valueMap.put("seriesTitle", new AttributeValue().withS(seriesTitle));
            valueMap.put("volumeNumber", new AttributeValue().withS(volumeNumber));
        }

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withLimit(PAGINATION_LIMIT)
                .withExclusiveStartKey(valueMap);


        ScanResultPage<ComicBook> scanList = mapper.scanPage(ComicBook.class, scanExpression);

        if(scanList == null) {
            metricsPublisher.addCount(
                    MetricsConstants.GETALLCOMICBOOKS_FAIL_COUNT, 1);
            throw new ComicBookNotFoundException("Comic book not found");
        }
        metricsPublisher.addCount(MetricsConstants.GETALLCOMICBOOKS_SUCCESS_COUNT, 0);
        return scanList.getResults();
    }
    
}
