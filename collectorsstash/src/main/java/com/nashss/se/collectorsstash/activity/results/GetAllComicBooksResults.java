package com.nashss.se.collectorsstash.activity.results;

import com.nashss.se.collectorsstash.models.ComicBookModel;

import java.util.ArrayList;
import java.util.List;

public class GetAllComicBooksResults {
    private final List<ComicBookModel> comicList;

    private GetAllComicBooksResults(List<ComicBookModel> comicList){
        this.comicList = comicList;
    }

    public List<ComicBookModel> getComicList() {
        return comicList;
    }

    @Override
    public String toString() {
        return "GetAllComicBooksResults{" +
                "comicList=" + comicList +
                '}';
    }

    /**
     *
     * @return new Builder of ComicModel
     */

    public static Builder builder() {
        return new Builder();
    }


    //CHECKSTYLE:OFF:Builder
    public static class Builder {
        private List<ComicBookModel> comicList;

        public Builder withComicList(List<ComicBookModel> comicList){
            this.comicList = comicList;
            return this;
        }


        public GetAllComicBooksResults build() {
            return new GetAllComicBooksResults(comicList);
        }

    }

}
