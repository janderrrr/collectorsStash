package com.nashss.se.collectorsstash.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

/**
 * Represents a Series in the DynamoDB table.
 */

@DynamoDBTable(tableName = "series")
public class Series {
    private String customerId;
    private String seriesId;
    private String title;
    private String volumeNumber;

    /**
     *
     * @return customerId, the partition key of the series in the Series table
     */
    @DynamoDBHashKey(attributeName = "customerId")
    public String getCustomerId() {
        return customerId;
    }
    /**
     *
     * @return seriesId, the range key of the series in the Series table
     */
    @DynamoDBRangeKey(attributeName = "seriesId")
    public String getSeriesId() {
        return seriesId;
    }

    /**
     *
     * @return title of the Series title
     */

    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return volumeNumber of the ComicBook volumeNumber
     */
    @DynamoDBAttribute(attributeName = "volumeNumber")
    public String getVolumeNumber() {
        return volumeNumber;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVolumeNumber(String volumeNumber) {
        this.volumeNumber = volumeNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return Objects.equals(customerId, series.customerId) && Objects.equals(seriesId, series.seriesId) && Objects.equals(title, series.title) && Objects.equals(volumeNumber, series.volumeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, seriesId, title, volumeNumber);
    }
}
