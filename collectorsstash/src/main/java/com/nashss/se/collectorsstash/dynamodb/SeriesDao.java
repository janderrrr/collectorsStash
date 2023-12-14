package com.nashss.se.collectorsstash.dynamodb;

import com.nashss.se.collectorsstash.dynamodb.models.Series;
import com.nashss.se.collectorsstash.exceptions.SeriesNotFoundException;
import com.nashss.se.collectorsstash.metrics.MetricsConstants;
import com.nashss.se.collectorsstash.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

import javax.inject.Inject;


public class SeriesDao {

    private static final int PAGINATION_LIMIT = 5;

    private final MetricsPublisher metricsPublisher;
    private final DynamoDBMapper mapper;

    /**
     * Constructs a new SeriesDao with the specified DynamoDBMapper and MetricsPublisher.
     * @param mapper           The DynamoDBMapper used for mapping Java objects to DynamoDB items.
     * @param metricsPublisher The MetricsPublisher used for tracking metrics related to Series operations.
     */
    @Inject
    public SeriesDao(DynamoDBMapper mapper, MetricsPublisher metricsPublisher) {
        this.mapper = mapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Retrieves a list of Series associated with the provided customer ID.
     *
     * <p>This method queries the data store to retrieve a list of Series items based on the
     * provided customer ID using the underlying mapper. If no Series items are found, a
     * {@link SeriesNotFoundException} is thrown with an appropriate error message.</p>
     *
     * <p>The method also increments the success or failure count metrics using the
     * {@link MetricsPublisher} associated with this instance.</p>
     *
     * @param customerId The ID of the customer for whom Series items are to be retrieved.
     * @return A list of Series associated with the specified customer ID.
     * @throws SeriesNotFoundException If no Series items are found for the specified customer.
     */
    public List<Series> getSeries(String customerId) {
        Series series = new Series();
        series.setCustomerId(customerId);


        DynamoDBQueryExpression<Series> queryExpression = new DynamoDBQueryExpression<Series>()
                .withHashKeyValues(series);

        QueryResultPage<Series> seriesList = mapper.queryPage(Series.class, queryExpression);


        if (seriesList == null) {
            metricsPublisher.addCount(MetricsConstants.GETSERIES_FAIL_COUNT, 1);
            throw new SeriesNotFoundException("Series Not Found");
        }
        metricsPublisher.addCount(MetricsConstants.GETSERIES_SUCCESS_COUNT, 1);
        return seriesList.getResults();
    }

    /**
     * Retrieves a specific Series identified by the provided customer ID and series ID.
     *
     * <p>This method retrieves the Series item from the data store based on the provided
     * customer ID and series ID using the underlying mapper. If the specified Series is not
     * found, a {@link SeriesNotFoundException} is thrown with an appropriate error message.</p>
     *
     * <p>The method also increments the success or failure count metrics using the
     * {@link MetricsPublisher} associated with this instance.</p>
     *
     * @param customerId The ID of the customer associated with the Series.
     * @param seriesId   The unique identifier for the Series to be retrieved.
     * @return The retrieved Series object.
     * @throws SeriesNotFoundException If the specified Series is not found.
     */
    public Series getOneSeries(String customerId, String seriesId) {
        Series series = this.mapper.load(Series.class, customerId, seriesId);

        if (series == null) {
            metricsPublisher.addCount(MetricsConstants.GETSERIES_FAIL_COUNT, 1);
            throw new SeriesNotFoundException("Could not find series " + seriesId);
        } else {
            metricsPublisher.addCount(MetricsConstants.GETSERIES_SUCCESS_COUNT, 1);
            return series;
        }
    }

    /**
     * Removes a Series identified by the provided customer ID and series ID.
     *
     * <p>This method deletes the specified Series item from the data store based on the
     * provided customer ID and series ID. If either the customer ID or series ID is null,
     * an {@link IllegalArgumentException} is thrown.</p>
     *
     * <p>If an error occurs during the deletion process, a {@link RuntimeException} is
     * thrown with a descriptive error message.</p>
     *
     * @param customerId The ID of the customer associated with the Series.
     * @param seriesId   The unique identifier for the Series to be removed.
     * @return The removed Series object.
     * @throws IllegalArgumentException If either the customer ID or series ID is null.
     * @throws RuntimeException         If an error occurs during the deletion process.
     */
    public Series removeSeries(String customerId, String seriesId) {
        if (seriesId == null || customerId == null) {
            throw new IllegalArgumentException("seriesId and customerId cannot be null");
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

    /**
     * Generates a unique Series ID using a random alphanumeric string.
     *
     * <p>The Series ID is created as a random string consisting of alphabetic characters,
     * ensuring uniqueness for each series created.</p>
     *
     * @return A randomly generated Series ID.
     */
    public String generateSeriesId() {
        return RandomStringUtils.randomAlphabetic(6);
    }
}
