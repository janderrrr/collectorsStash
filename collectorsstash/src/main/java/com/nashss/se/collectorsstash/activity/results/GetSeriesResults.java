package com.nashss.se.collectorsstash.activity.results;

import com.nashss.se.collectorsstash.models.SeriesModel;

import java.util.List;

public class GetSeriesResults {
    private final List<SeriesModel> seriesList;
    /**
     * Constructs a new result object for the retrieval of series.
     *
     * @param seriesList The list of {@link SeriesModel}s representing the retrieved series.
     */
    public GetSeriesResults(List<SeriesModel> seriesList) {
        this.seriesList = seriesList;
    }

    public List<SeriesModel> getSeriesList() {
        return seriesList;
    }

    @Override
    public String toString() {
        return "GetSeriesResults{" +
                "seriesList=" + seriesList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private List<SeriesModel> seriesList;

        public Builder withSeriesList(List<SeriesModel> seriesList){
            this.seriesList = seriesList;
            return this;
        }

        public GetSeriesResults build(){
            return new GetSeriesResults(seriesList);
        }
    }

}
