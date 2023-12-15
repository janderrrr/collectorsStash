package com.nashss.se.collectorsstash.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateComicBookRequest.Builder.class)
public class CreateComicBookRequest {
    private final String customerId;
    private final String seriesId;
    private final String title;
    private final String issueNumber;
    private final String volumeNumber;
    private final Boolean isFavorite;
    private final int price;
    private final String publisher;
    private final String year;
    /**
     * Constructs a new request object for creating a comic book.
     *
     * @param customerId   The ID of the customer associated with the comic book.
     * @param seriesId     The ID of the series to which the comic book belongs.
     * @param title        The title of the comic book.
     * @param issueNumber  The issue number of the comic book.
     * @param volumeNumber The volume number of the comic book.
     * @param isFavorite   The favorite status of the comic book.
     * @param price        The price of the comic book.
     * @param publisher    The publisher of the comic book.
     * @param year         The publication year of the comic book.
     */
    private CreateComicBookRequest(String customerId, String seriesId, String title, String issueNumber,
                                  String volumeNumber, Boolean isFavorite, int price, String publisher, String year) {
        this.customerId = customerId;
        this.seriesId = seriesId;
        this.title = title;
        this.issueNumber = issueNumber;
        this.volumeNumber = volumeNumber;
        this.isFavorite = isFavorite;
        this.price = price;
        this.publisher = publisher;
        this.year = year;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public String getTitle() {
        return title;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public String getVolumeNumber() {
        return volumeNumber;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public int getPrice() {
        return price;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "CreateComicBookRequest{" +
                "customerId='" + customerId + '\'' +
                ", seriesId='" + seriesId + '\'' +
                ", title='" + title + '\'' +
                ", issueNumber='" + issueNumber + '\'' +
                ", volumeNumber='" + volumeNumber + '\'' +
                ", isFavorite=" + isFavorite +
                ", price=" + price +
                ", publisher='" + publisher + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {return new Builder();}

    @JsonPOJOBuilder
    public static class Builder {
        private String customerId;
        private String seriesId;
        private String title;
        private String issueNumber;
        private String volumeNumber;
        private Boolean isFavorite;
        private int price;
        private String publisher;
        private String year;

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withSeriesId(String seriesId) {
            this.seriesId = seriesId;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withIssueNumber(String issueNumber) {
            this.issueNumber = issueNumber;
            return this;
        }

        public Builder withVolumeNumber(String volumeNumber) {
            this.volumeNumber = volumeNumber;
            return this;
        }

        public Builder withIsFavorite(Boolean isFavorite) {
            this.isFavorite = isFavorite;
            return this;
        }

        public Builder withPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder withYear(String year) {
            this.year = year;
            return this;
        }

        public Builder withPrice(int price) {
            this.price = price;
            return this;
        }

        public CreateComicBookRequest build() {
            return new CreateComicBookRequest(customerId, seriesId, title, issueNumber,
                    volumeNumber, isFavorite, price, publisher, year);
        }
    }
}
