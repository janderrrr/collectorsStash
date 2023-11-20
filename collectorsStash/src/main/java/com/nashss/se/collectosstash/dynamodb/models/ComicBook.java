package com.nashss.se.collectosstash.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;


/**
 * Represents a Vendor in the DynamoDB table.
 */

@DynamoDBTable(tableName = "ComicBooks")
public class ComicBook {
    private String seriesTitle;
    private String volumeNumber;
    private String issueNumber;
    private String date;
    private int price;
    private boolean isFavorite;
    private String publisher;


    /**
     *
     * @return id, the partition key of the comicBook in the ComicBooks table
     */
    @DynamoDBHashKey(attributeName = "seriesTitle")
    public String getSeriesTitle() {
        return seriesTitle;
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


    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicBook comicBook = (ComicBook) o;
        return price == comicBook.price && isFavorite == comicBook.isFavorite &&
                Objects.equals(seriesTitle, comicBook.seriesTitle) &&
                Objects.equals(volumeNumber, comicBook.volumeNumber) &&
                Objects.equals(issueNumber, comicBook.issueNumber) &&
                Objects.equals(date, comicBook.date) && Objects.equals(publisher, comicBook.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seriesTitle, volumeNumber, issueNumber, date, price, isFavorite, publisher);
    }
}
