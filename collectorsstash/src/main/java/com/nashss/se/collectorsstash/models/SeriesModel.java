package com.nashss.se.collectorsstash.models;

import java.util.Objects;

public class SeriesModel {
    private final String customerId;
    private final String seriesId;
    private final String title;
    private final String volumeNumber;

    /**
     * Constructs a new SeriesModel with the specified parameters.
     *
     * @param customerId    The ID of the customer associated with the series.
     * @param seriesId      The unique identifier for the series.
     * @param title         The title of the series.
     * @param volumeNumber  The volume number of the series.
     */
    public SeriesModel(String customerId, String seriesId, String title, String volumeNumber) {
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
        return "SeriesModel{" +
                "customerId='" + customerId + '\'' +
                ", seriesId='" + seriesId + '\'' +
                ", title='" + title + '\'' +
                ", volumeNumber='" + volumeNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SeriesModel that = (SeriesModel) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(seriesId, that.seriesId) &&
                Objects.equals(title, that.title) && Objects.equals(volumeNumber, that.volumeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, seriesId, title, volumeNumber);
    }

    //CHECKSTYLE:OFF:BUILDER
    public static Builder builder(){
        return new Builder();
    }

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

        public SeriesModel build() {
            return new SeriesModel(customerId, seriesId, title, volumeNumber);
        }
    }
}
