package com.nashss.se.collectorsstash.activity.results;

import com.nashss.se.collectorsstash.models.SeriesModel;

public class CreateSeriesResults {
    private final SeriesModel series;

    private CreateSeriesResults(SeriesModel series) {
        this.series = series;
    }

    public SeriesModel getSeries() {
        return series;
    }

    @Override
    public String toString() {
        return "CreateSeriesResults{" +
                "series=" + series +
                '}';
    }
    //CHECKSTYLE:OFF:Builder
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private SeriesModel series;

        public Builder withSeries(SeriesModel series) {
            this.series = series;
            return this;
        }

        public CreateSeriesResults build() {
            return new CreateSeriesResults(series);
        }
    }
}
