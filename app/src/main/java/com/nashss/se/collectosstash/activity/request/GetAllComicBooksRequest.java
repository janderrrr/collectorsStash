package com.nashss.se.collectosstash.activity.request;

public class GetAllComicBooksRequest {
    private String seriesTitle;
    private String volumeNumber;

    private GetAllComicBooksRequest(String seriesTitle, String volumeNumber) {
        this.seriesTitle = seriesTitle;
        this.volumeNumber = volumeNumber;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public String getVolumeNumber() {
        return volumeNumber;
    }

    @Override
    public String toString() {
        return "GetAllComicBooksRequest{" +
                "seriesTitle='" + seriesTitle + '\'' +
                ", volumeNumber='" + volumeNumber + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }
    /**
     *
     * @return new Builder of ComicModel
     */
    public static class Builder {
        private String seriesTitle;
        private String volumeNumber;

        /**
         * @param buildSeriesTitle  String
         * @return seriesTitle
         */

        public Builder withSeriesTitle(String buildSeriesTitle){
            this.seriesTitle = buildSeriesTitle;
            return this;
        }
        /**
         * @param buildVolumeNumber String
         * @return volumeNumber
         */
        public Builder withVolumeNumber(String buildVolumeNumber) {
            this.volumeNumber = buildVolumeNumber;
            return this;
        }

        /**
         *
         * @return Request with passed in series and volumeNumber
         */
        public GetAllComicBooksRequest build(){
            return new GetAllComicBooksRequest(seriesTitle, volumeNumber);
        }

    }

}
