package com.nashss.se.collectorsstash.activity.request;

public class RemoveSeriesRequest {
    private final String customerId;
    private final String seriesId;


    public RemoveSeriesRequest(String customerId, String seriesId) {
        this.customerId = customerId;
        this.seriesId = seriesId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getSeriesId() {
        return seriesId;
    }

    @Override
    public String toString() {
        return "RemoveSeriesRequest{" +
                "customerId='" + customerId + '\'' +
                ", seriesId='" + seriesId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String customerId;

        private String seriesId;

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withSeriesId(String seriesId) {
            this.seriesId = seriesId;
            return this;
        }


        public RemoveSeriesRequest build() {
            return new RemoveSeriesRequest(customerId, seriesId);
        }
    }
}
