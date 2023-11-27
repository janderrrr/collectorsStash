package com.nashss.se.collectorsstash.activity.request;

public class GetSeriesRequest {
    private final String customerId;
    private final String title;
    private final String volumeNumber;

    public GetSeriesRequest(String customerId, String title, String volumeNumber) {
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
        return "GetSeriesRequest{" +
                "customerId='" + customerId + '\'' +
                ", title='" + title + '\'' +
                ", volumeNumber='" + volumeNumber + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String customerId;
        private String title;
        private String volumeNumber;

        public Builder withCustomerId(String customerId){
            this.customerId = customerId;
            return this;
        }

        public Builder withTitle(String title){
            this.title = title;
            return this;
        }

        public Builder withVolumeNumber(String volumeNumber){
            this.volumeNumber = volumeNumber;
            return this;
        }
        public GetSeriesRequest build() {
            return new GetSeriesRequest(customerId, title, volumeNumber);
        }
    }
}
