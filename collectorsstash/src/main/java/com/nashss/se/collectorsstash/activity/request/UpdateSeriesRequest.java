package com.nashss.se.collectorsstash.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateSeriesRequest.Builder.class)
public class UpdateSeriesRequest {
    private final String customerId;
    private final String seriesId;
    private final String title;
    private final String volumeNumber;
    /**
     * Constructs a new request object for updating information about a specific series.
     *
     * @param customerId   The ID of the customer associated with the series to be updated.
     * @param seriesId     The ID of the series to be updated.
     * @param title        The updated title for the series.
     * @param volumeNumber The updated volume number for the series.
     */
    private UpdateSeriesRequest(String customerId, String seriesId, String title, String volumeNumber) {
        this.customerId = customerId;
        this.seriesId = seriesId;
        this.title = title;
        this.volumeNumber = volumeNumber;
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

    public String getVolumeNumber() {
        return volumeNumber;
    }

    @Override
    public String toString() {
        return "UpdateSeriesRequest{" +
                "customerId='" + customerId + '\'' +
                ", seriesId='" + seriesId + '\'' +
                ", title='" + title + '\'' +
                ", volumeNumber='" + volumeNumber + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder

    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {
        private String customerId;
        private String seriesId;
        private String title;
        private String volumeNumber;

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

        public Builder withVolumeNumber(String volumeNumber) {
            this.volumeNumber = volumeNumber;
            return this;
        }

        public UpdateSeriesRequest build() {
            return new UpdateSeriesRequest(customerId, seriesId, title, volumeNumber);
        }
    }
}
