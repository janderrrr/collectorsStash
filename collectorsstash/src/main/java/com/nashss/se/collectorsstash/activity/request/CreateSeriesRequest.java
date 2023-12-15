package com.nashss.se.collectorsstash.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateSeriesRequest.Builder.class)
public class CreateSeriesRequest {
    private final String customerId;
    private final String title;
    private final String volumeNumber;
    /**
     * Constructs a new request object for creating a series.
     *
     * @param customerId   The ID of the customer creating the series.
     * @param title        The title of the series.
     * @param volumeNumber The volume number of the series.
     */
    private CreateSeriesRequest(String customerId, String title, String volumeNumber) {
        this.customerId = customerId;
        this.title = title;
        this.volumeNumber = volumeNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getTitle() {
        return title;
    }

    public String getVolumeNumber() {
        return volumeNumber;
    }

    @Override
    public String toString() {
        return "CreateSeriesRequest{" +
                "customerId='" + customerId + '\'' +
                ", title='" + title + '\'' +
                ", volumeNumber='" + volumeNumber + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {return new Builder();}

    @JsonPOJOBuilder
    public static class Builder {
        private String customerId;
        private String title;
        private String volumeNumber;

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

        public CreateSeriesRequest build() {
            return new CreateSeriesRequest(customerId, title, volumeNumber);
        }

    }

}
