package com.nashss.se.collectorsstash.activity.results;

import com.nashss.se.collectorsstash.activity.request.UpdateSeriesRequest;
import com.nashss.se.collectorsstash.models.SeriesModel;

public class UpdateSeriesResult {
    private final SeriesModel seriesModel;

    public UpdateSeriesResult(SeriesModel seriesModel) {
        this.seriesModel = seriesModel;
    }
    public SeriesModel getSeriesModel() {
        return seriesModel;
    }

    @Override
    public String toString() {
        return "UpdateSeriesResult{" +
                "seriesModel=" + seriesModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private SeriesModel seriesModel;

        public Builder withSeriesModel(SeriesModel seriesModel) {
            this.seriesModel = seriesModel;
            return this;
        }

        public UpdateSeriesResult build() {
            return new UpdateSeriesResult(seriesModel);
        }
    }
}
