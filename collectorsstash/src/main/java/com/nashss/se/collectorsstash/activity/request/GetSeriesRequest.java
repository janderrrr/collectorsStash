package com.nashss.se.collectorsstash.activity.request;

public class GetSeriesRequest {
    private final String customerId;

    public GetSeriesRequest(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }


    @Override
    public String toString() {
        return "GetSeriesRequest{" +
                "customerId='" + customerId + '\'' +
                '}';
    }

//CHECKSTYLE:OFF:Builder

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String customerId;

        public Builder withCustomerId(String customerId){
            this.customerId = customerId;
            return this;
        }

        public GetSeriesRequest build() {
            return new GetSeriesRequest(customerId);
        }
    }
}
