package com.nashss.se.collectorsstash.activity.request;

public class GetPriceComicBookRequest {
    private final String seriesId;
    private final int price;
    /**
     * Constructs a new request object for retrieving comic books based on a specified price within a series.
     *
     * @param seriesId The ID of the series for which comic books are being queried.
     * @param price    The maximum price to filter the comic books within the specified series.
     */
    public GetPriceComicBookRequest(String seriesId, int price) {
        this.seriesId = seriesId;
        this.price = price;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "GetPriceComicBookRequest{" +
                "seriesId='" + seriesId + '\'' +
                ", price=" + price +
                '}';
    }

    //CHECKSTYLE:OFF:Builder

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String seriesId;
        private int price;

        public Builder withSeriesId(String seriesId) {
            this.seriesId = seriesId;
            return this;
        }

        public Builder withPrice(int price) {
            this.price = price;
            return this;
        }


        public GetPriceComicBookRequest build() {
            return new GetPriceComicBookRequest(seriesId, price);
        }
    }
}
