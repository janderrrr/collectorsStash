package com.nashss.se.collectorsstash.activity.results;

import com.nashss.se.collectorsstash.models.ComicBookModel;

import java.util.List;

public class GetPriceComicBookResult {
    private final List<ComicBookModel> comicList;

    private GetPriceComicBookResult(List<ComicBookModel> comicList) {
        this.comicList = comicList;
    }

    public List<ComicBookModel> getComicList() {
        return comicList;
    }

    @Override
    public String toString() {
        return "GetPriceComicBookResult{" +
                "comicList=" + comicList +
                '}';
    }
    /**
     * Creates a new builder instance for constructing a {@link
     * com.nashss.se.collectorsstash.activity.request.GetPriceComicBookRequest}.
     *
     * @return A new instance of the {@link Builder} class to build a {@link GetPriceComicBookResult}.
     */
    public static Builder builder() {
        return new Builder();
    }

    //CHECKSTYLE:OFF:Builder
    public static class Builder {
        private List<ComicBookModel> comicList;

        public Builder withComicList(List<ComicBookModel> comicList) {
            this.comicList = comicList;
            return this;
        }

        public GetPriceComicBookResult build() {
            return new GetPriceComicBookResult(comicList);
        }
    }
}
