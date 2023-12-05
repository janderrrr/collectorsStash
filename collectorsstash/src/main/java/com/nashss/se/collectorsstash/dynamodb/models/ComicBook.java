package com.nashss.se.collectorsstash.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;


/**
 * Represents a ComicBook in the DynamoDB table.
 */

@DynamoDBTable(tableName = "comicbooks")
public class ComicBook {
    private String seriesId;
    private String issueNumber;
    private String title;
    private String volumeNumber;
    private String year;
    private int price;
    private boolean isFavorite;
    private String publisher;

    @DynamoDBHashKey(attributeName = "seriesId")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicBook comicBook = (ComicBook) o;
        return price == comicBook.price && isFavorite == comicBook.isFavorite && Objects.equals(seriesId, comicBook.seriesId) && Objects.equals(issueNumber, comicBook.issueNumber) && Objects.equals(title, comicBook.title) && Objects.equals(volumeNumber, comicBook.volumeNumber) && Objects.equals(year, comicBook.year) && Objects.equals(publisher, comicBook.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seriesId, issueNumber, title, volumeNumber, year, price, isFavorite, publisher);
    }
}
