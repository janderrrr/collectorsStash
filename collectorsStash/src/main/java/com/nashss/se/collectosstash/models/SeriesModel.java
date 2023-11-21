package com.nashss.se.collectosstash.models;

import java.util.Objects;

public class SeriesModel {
    private final String customerId;
    private final String title;
    private final String volumeNumber;

    public SeriesModel(String customerId, String title, String volumeNumber) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeriesModel that = (SeriesModel) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(title, that.title) && Objects.equals(volumeNumber, that.volumeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, title, volumeNumber);
    }

    //CHECKSTYLE:OFF:BUILDER
    public static Builder builder(){
        return new Builder();
    }

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

        public SeriesModel build() {
            return new SeriesModel(customerId, title, volumeNumber);
        }
    }
}
