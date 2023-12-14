package com.nashss.se.collectorsstash.activity.results;

import com.nashss.se.collectorsstash.models.SeriesModel;

public class RemoveSeriesResult {
    private final SeriesModel seriesModel;
    /**
     * Constructs a new result object for the removal of a series.
     *
     * @param seriesModel The {@link SeriesModel} representing the removed series.
     */
    public RemoveSeriesResult(SeriesModel seriesModel) {
        this.seriesModel = seriesModel;
    }

    public SeriesModel getSeriesModel() {
        return seriesModel;
    }

    @Override
    public String toString() {
        return "RemoveSeriesResult{" +
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

        public RemoveSeriesResult build() {
            return new RemoveSeriesResult(seriesModel);
        }
    }
}
