package com.nashss.se.collectorsstash.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

import java.util.Objects;

/**
 * Represents a ComicBook in the DynamoDB table.
 */

@DynamoDBTable(tableName = "comicbooks")
public class ComicBook {
    private String seriesId;
    private String issueNumber;
    private String title;
    private String customerId;
    private String volumeNumber;
    private String year;
    private int price;
    private boolean isFavorite;
    private String publisher;

    @DynamoDBHashKey(attributeName = "seriesId")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "FavComicIndex", attributeName = "seriesId")
    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    @DynamoDBRangeKey(attributeName = "issueNumber")
    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute(attributeName = "volumeNumber")
    public String getVolumeNumber() {
        return volumeNumber;
    }

    public void setVolumeNumber(String volumeNumber) {
        this.volumeNumber = volumeNumber;
    }

    @DynamoDBAttribute(attributeName = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @DynamoDBAttribute(attributeName = "price")
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "FavComicIndex", attributeName = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @DynamoDBAttribute(attributeName = "isFavorite")
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @DynamoDBAttribute(attributeName = "publisher")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @DynamoDBAttribute(attributeName = "customerId")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ComicBook comicBook = (ComicBook) o;
        return price == comicBook.price && isFavorite == comicBook.isFavorite &&
                Objects.equals(seriesId, comicBook.seriesId) &&
                Objects.equals(issueNumber, comicBook.issueNumber) &&
                Objects.equals(title, comicBook.title) && Objects.equals(customerId, comicBook.customerId) &&
                Objects.equals(volumeNumber, comicBook.volumeNumber) &&
                Objects.equals(year, comicBook.year) && Objects.equals(publisher, comicBook.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seriesId, issueNumber, title, customerId, volumeNumber, year, price, isFavorite, publisher);
    }
}
