package com.nashss.se.collectorsstash.activity.request;

public class GetAllComicBooksRequest {
    private String seriesId;

    private GetAllComicBooksRequest(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesId() {
        return seriesId;
    }

    @Override
    public String toString() {
        return "GetAllComicBooksRequest{" +
                "seriesId='" + seriesId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    /**
     *
     * @return new Builder of ComicModel
     */
    public static class Builder {
        private String seriesId;

        /**
         * @param seriesId String
         * @return seriesId
         */

        public Builder withSeriesId(String seriesId){
            this.seriesId = seriesId;
            return this;
        }

        /**
         *
         * @return Request with passed in seriesId
         */
        public GetAllComicBooksRequest build(){
            return new GetAllComicBooksRequest(seriesId);
        }

    }

}