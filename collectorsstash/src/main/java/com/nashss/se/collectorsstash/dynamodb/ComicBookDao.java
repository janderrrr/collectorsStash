package com.nashss.se.collectorsstash.dynamodb;

import com.nashss.se.collectorsstash.dynamodb.models.ComicBook;
import com.nashss.se.collectorsstash.exceptions.ComicBookNotFoundException;
import com.nashss.se.collectorsstash.metrics.MetricsConstants;
import com.nashss.se.collectorsstash.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;




@Singleton
public class ComicBookDao {

    private static final int PAGINATION_LIMIT = 5;
    private final MetricsPublisher metricsPublisher;
    private final DynamoDBMapper mapper;

    /**
     * Constructs a new ComicBookDao with the specified DynamoDBMapper and MetricsPublisher.
     *
     * @param mapper           The DynamoDBMapper used for mapping Java objects to DynamoDB items.
     * @param metricsPublisher The MetricsPublisher used for tracking metrics related to ComicBook operations.
     */
    @Inject
    public ComicBookDao(DynamoDBMapper mapper, MetricsPublisher metricsPublisher) {
        this.mapper = mapper;
        this.metricsPublisher = metricsPublisher;
    }
    /**
     * Retrieves a list of all ComicBooks associated with a specified series.
     *
     * <p>This method queries the data store to retrieve a paginated list of ComicBook items from the specified
     * series using the provided DynamoDBMapper. If no ComicBook items are found for the specified series,
     * a {@link ComicBookNotFoundException} is thrown with an appropriate errorr message.</p>
     *
     * <p>The method also increments the success or failure count metrics using the
     * {@link MetricsPublisher} associated with this instance.</p>
     *
     * @param seriesId The unique identifier for the series.
     * @return A paginated list of ComicBooks associated with the specified series.
     * @throws ComicBookNotFoundException If no ComicBook items are found for the specified series.
     */
    public List<ComicBook> getAllComicBooks(String seriesId) {
        ComicBook comicBook = new ComicBook();
        comicBook.setSeriesId(seriesId);

        DynamoDBQueryExpression<ComicBook> queryExpression = new DynamoDBQueryExpression<ComicBook>()
                .withHashKeyValues(comicBook)
                .withLimit(PAGINATION_LIMIT);

        QueryResultPage<ComicBook> comicList = mapper.queryPage(ComicBook.class, queryExpression);

        if (comicList == null) {
            metricsPublisher.addCount(
                    MetricsConstants.GETALLCOMICBOOKS_FAIL_COUNT, 1);
            throw new ComicBookNotFoundException("Comic book not found");
        }
        metricsPublisher.addCount(MetricsConstants.GETALLCOMICBOOKS_SUCCESS_COUNT, 0);
        return comicList.getResults();

    }
    /**
     * Saves a ComicBook object to the data store.
     *
     * @param comicbook The ComicBook object to be saved.
     * @return The saved ComicBook object.
     */
    public ComicBook comicSave(ComicBook comicbook) {
        this.mapper.save(comicbook);
        return comicbook;
    }

    /**
     * Retrieves a list of ComicBooks from a specified series with a price equal to or greater than the provided value.
     *
     * <p>If no ComicBook items are found for the specified series and price, a {@link ComicBookNotFoundException}
     * is thrown with an appropriate error message.</p>
     *
     * @param seriesId The unique identifier for the series.
     * @param price    The minimum price threshold for ComicBooks to be retrieved.
     * @return A list of ComicBooks from the specified series with a price equal to or greater than the provided value.
     * @throws ComicBookNotFoundException If no ComicBook items are found for the specified series and price.
     */
    public List<ComicBook> getComicByPrice(String seriesId, int price) {
        ComicBook comicBook = new ComicBook();
        comicBook.setSeriesId(seriesId);

        DynamoDBQueryExpression<ComicBook> queryExpression = new DynamoDBQueryExpression<ComicBook>()
                .withIndexName("PriceComicIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("seriesId = :seriesId and price >= :priceValue")
                .withExpressionAttributeValues(
                        Map.of(":seriesId", new AttributeValue().withS(seriesId),
                                ":priceValue", new AttributeValue().withN(Integer.toString(price)))
                )
                .withLimit(PAGINATION_LIMIT);

        QueryResultPage<ComicBook> comicList = mapper.queryPage(ComicBook.class, queryExpression);

        if (comicList == null) {
            metricsPublisher.addCount(MetricsConstants.GETALLCOMICBOOKS_FAIL_COUNT, 1);
            throw new ComicBookNotFoundException("Comic book not found");
        }

        metricsPublisher.addCount(MetricsConstants.GETALLCOMICBOOKS_SUCCESS_COUNT, 0);
        return comicList.getResults();
    }
}
