package com.nashss.se.collectorsstash.models;

import java.util.Objects;

public class ComicBookModel {
    private final String seriesId;
    private final String customerId;
    private final String title;
    private final String volumeNumber;
    private final String issueNumber;
    private final String year;
    private final int price;
    private final Boolean isFavorite;
    private final String publisher;

    /**
     * Constructs a new ComicBookModel with the specified parameters.
     *
     * @param seriesId      The unique identifier for the series associated with the comic book.
     * @param customerId    The ID of the customer associated with the comic book.
     * @param title         The title of the comic book.
     * @param volumeNumber  The volume number of the comic book.
     * @param issueNumber   The issue number of the comic book.
     * @param year          The publication year of the comic book.
     * @param price         The price of the comic book.
     * @param isFavorite    Indicates whether the comic book is marked as a favorite.
     * @param publisher     The publisher of the comic book.
     */
    private ComicBookModel(String seriesId, String customerId, String title, String volumeNumber,
                          String issueNumber, String year, int price, Boolean isFavorite, String publisher) {
        this.seriesId = seriesId;
        this.customerId = customerId;
        this.title = title;
        this.volumeNumber = volumeNumber;
        this.issueNumber = issueNumber;
        this.year = year;
        this.price = price;
        this.isFavorite = isFavorite;
        this.publisher = publisher;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public String getTitle() {
        return title;
    }

    public String getVolumeNumber() {
        return volumeNumber;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public String getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getCustomerId() {
        return customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ComicBookModel that = (ComicBookModel) o;
        return price == that.price && Objects.equals(seriesId, that.seriesId) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(volumeNumber, that.volumeNumber) && Objects.equals(issueNumber, that.issueNumber) &&
                Objects.equals(year, that.year) && Objects.equals(isFavorite, that.isFavorite) &&
                Objects.equals(publisher, that.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seriesId, customerId, title, volumeNumber, issueNumber, year, price, isFavorite, publisher);
    }

    @Override
    public String toString() {
        return "ComicBookModel{" +
                "seriesId='" + seriesId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", title='" + title + '\'' +
                ", volumeNumber='" + volumeNumber + '\'' +
                ", issueNumber='" + issueNumber + '\'' +
                ", year='" + year + '\'' +
                ", price=" + price +
                ", isFavorite=" + isFavorite +
                ", publisher='" + publisher + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:BUILDER
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private String seriesId;
        private String customerId;
        private String title;
        private String volumeNumber;
        private String issueNumber;
        private String year;
        private int price;
        private Boolean isFavorite;
        private String publisher;

        public Builder withSeriesId(String seriesId) {
            this.seriesId = seriesId;
            return this;
        }
        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }
        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withVolumeNumber(String volumeNumber) {
            this.volumeNumber = volumeNumber;
            return this;
        }

        public Builder withIssueNumber(String issueNumber) {
            this.issueNumber = issueNumber;
            return this;
        }

        public Builder withYear(String year){
            this.year = year;
            return this;
        }

        public Builder withPrice(int price){
            this.price = price;
            return this;
        }

        public Builder withIsFavorite(Boolean isFavorite){
            this.isFavorite = isFavorite;
            return this;
        }

        public Builder withPublisher(String publisher){
            this.publisher = publisher;
            return this;
        }

        public ComicBookModel build(){
            return new ComicBookModel(seriesId,customerId, title, volumeNumber, issueNumber, year, price, isFavorite, publisher);
        }
    }

}
