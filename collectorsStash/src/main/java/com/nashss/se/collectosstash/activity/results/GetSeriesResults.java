package com.nashss.se.collectosstash.activity.results;

import com.nashss.se.collectosstash.activity.request.GetSeriesRequest;
import com.nashss.se.collectosstash.models.ComicBookModel;
import com.nashss.se.collectosstash.models.SeriesModel;

import java.util.ArrayList;
import java.util.List;

public class GetSeriesResults {
    private final List<SeriesModel> seriesList;

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
