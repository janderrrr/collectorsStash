package com.nashss.se.collectorsstash.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.collectorsstash.dynamodb.models.ComicBook;
import com.nashss.se.collectorsstash.exceptions.ComicBookNotFoundException;
import com.nashss.se.collectorsstash.metrics.MetricsConstants;
import com.nashss.se.collectorsstash.metrics.MetricsPublisher;
import org.checkerframework.checker.units.qual.C;

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
    public ComicBookDao(DynamoDBMapper mapper, MetricsPublisher metricsPublisher) {
        this.mapper = mapper;
        this.metricsPublisher = metricsPublisher;
    }

    public List<ComicBook> getAllComicBooks(String seriesId){
        ComicBook comicBook = new ComicBook();
        comicBook.setSeriesId(seriesId);

        DynamoDBQueryExpression<ComicBook> queryExpression = new DynamoDBQueryExpression<ComicBook>()
                .withHashKeyValues(comicBook)
                .withLimit(PAGINATION_LIMIT);

        QueryResultPage<ComicBook> comicList = mapper.queryPage(ComicBook.class, queryExpression);

        if(comicList == null) {
            metricsPublisher.addCount(
                    MetricsConstants.GETALLCOMICBOOKS_FAIL_COUNT, 1);
            throw new ComicBookNotFoundException("Comic book not found");
        }
        metricsPublisher.addCount(MetricsConstants.GETALLCOMICBOOKS_SUCCESS_COUNT, 0);
        return comicList.getResults();

    }
//
//    public ComicBook getOneComicBook(String seriesId, String issueNumber) {
//        ComicBook comicBook = new ComicBook();
//    }

}
