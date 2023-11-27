package com.nashss.se.collectorsstash.activity.results;

import com.nashss.se.collectorsstash.models.ComicBookModel;

import java.util.ArrayList;
import java.util.List;

public class GetAllComicBooksResults {
    private final List<ComicBookModel> comicList;
    private String seriesTitle;
    private String volumeNumber;

    private GetAllComicBooksResults(List<ComicBookModel> comicList, String seriesTitle, String volumeNumber){
        this.comicList = comicList;
        this.seriesTitle = seriesTitle;
        this.volumeNumber = volumeNumber;
    }

    public List<ComicBookModel> getComicList() {
        return new ArrayList<>();
    }

    public String getSeriesTitle(){
        return seriesTitle;
    }

    public String getVolumeNumber(){
        return volumeNumber;
    }

    @Override
    public String toString() {
        return "GetAllComicBooksResults{" +
                "comicList=" + comicList +
                ", seriesTitle='" + seriesTitle + '\'' +
                ", volumeNumber='" + volumeNumber + '\'' +
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
        private String seriesTitle;
        private String volumeNumber;

        public Builder withComicList(List<ComicBookModel> comicList){
            this.comicList = comicList;
            return this;
        }

        public Builder withSeriesTitle(String seriesTitle){
            this.seriesTitle = seriesTitle;
            return this;
        }

        public Builder withVolumeNumber(String volumeNumber){
            this.volumeNumber = volumeNumber;
            return this;
        }

        public GetAllComicBooksResults build() {
            return new GetAllComicBooksResults(comicList, seriesTitle, volumeNumber);
        }

    }

}
