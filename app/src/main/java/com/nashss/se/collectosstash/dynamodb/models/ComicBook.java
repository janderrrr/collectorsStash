package com.nashss.se.collectosstash.dynamodb.models;

import com.amazonaws.internal.config.Builder;
import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;

/**
 * Represents a Vendor in the DynamoDB table.
 */

@DynamoDBTable(tableName = "ComicBooks")
public class ComicBook {
    private String titleId;
    private String volumeNumber;
    private String issueNumber;
    private String date;
    private int price;
    private boolean isFavorite;
    private String publisher;


    public ComicBook(String titleId, String volumeNumber, String issueNumber, String date, int price, boolean isFavorite, String publisher) {
        this.titleId = titleId;
        this.volumeNumber = volumeNumber;
        this.issueNumber = issueNumber;
        this.date = date;
        this.price = price;
        this.isFavorite = isFavorite;
        this.publisher = publisher;
    }

    /**
     *
     * @return id, the partition key of the comicBook in the ComicBooks table
     */
    @DynamoDBHashKey(attributeName = "titleId")
    public String getId() {
        return titleId;
    }

    /**
     *
     * @return volumeNumber of the ComicBook volumeNumber
     */
    @DynamoDBRangeKey(attributeName = "volumeNumber")
    public String getVolumeNumber() {
        return volumeNumber;
    }
    /**
     *
     * @return issueNumber of the ComicBook issueNumber
     */
    @DynamoDBAttribute(attributeName = "issueNumber")
    public String getIssueNumber() {
        return issueNumber;
    }

    @DynamoDBAttribute(attributeName = "date")
    public String getDate() {
        return date;
    }

    @DynamoDBAttribute(attributeName = "price")
    public int getPrice() {
        return price;
    }

    @DynamoDBAttribute(attributeName = "isFavorite")
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    public boolean isFavorite() {
        return isFavorite;
    }

    @DynamoDBAttribute(attributeName = "publisher")
    public String getPublisher() {
        return publisher;
    }


    public void setId(String id) {
        this.titleId = id;
    }

    public void setVolumeNumber(String volumeNumber) {
        this.volumeNumber = volumeNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
