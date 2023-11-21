package com.nashss.se.collectosstash.activity.request;

public class GetSeriesRequest {
    private String customerId;

    private GetSeriesRequest(String customerId){
        this.customerId = customerId;
    }
    public String getCustomerId(){
        return customerId;
    }
    @Override
    public String toString() {
        return "GetSeriesRequest{" +
                "customerId='" + customerId + '\'' +
                '}';
    }
    //CHECKSTYLE:OFF:Builder
    public static GetAllComicBooksRequest.Builder builder() {
        return new GetAllComicBooksRequest.Builder();
    }
    /**
     *
     * @return new Builder of ComicModel
     */
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
