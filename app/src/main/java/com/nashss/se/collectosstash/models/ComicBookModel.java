package com.nashss.se.collectosstash.models;

import java.util.Objects;

public class ComicBookModel {
    private final String id;
    private final String volumeNumber;
    private final String issueNumber;
    private final String date;
    private final int price;
    private final Boolean isFavorite;
    private final String publisher;


    public ComicBookModel(String id, String volumeNumber, String issueNumber, String date, int price, Boolean isFavorite, String publisher) {
        this.id = id;
        this.volumeNumber = volumeNumber;
        this.issueNumber = issueNumber;
        this.date = date;
        this.price = price;
        this.isFavorite = isFavorite;
        this.publisher = publisher;
    }

    public String getId() {
        return id;
    }

    public String getVolumeNumber() {
        return volumeNumber;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public String getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public String getPublisher() {
        return publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicBookModel that = (ComicBookModel) o;
        return price == that.price && Objects.equals(id, that.id) && Objects.equals(volumeNumber, that.volumeNumber) && Objects.equals(issueNumber, that.issueNumber) && Objects.equals(date, that.date) && Objects.equals(isFavorite, that.isFavorite) && Objects.equals(publisher, that.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, volumeNumber, issueNumber, date, price, isFavorite, publisher);
    }
    //CHECKSTYLE:OFF:BUILDER
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String volumeNumber;
        private String issueNumber;
        private String date;
        private int price;
        private Boolean isFavorite;
        private String publisher;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withVolumeNumber(String volumeNumber) {
            this.volumeNumber = volumeNumber;
            return this;
        }

        public Builder withIssueNumber(String issueNumber) {
            this.issueNumber = issueNumber;
            return this;
        }

        public Builder withDate(String date){
            this.date = date;
            return this;
        }

        public Builder withPrice(int price){
            this.price = price;
            return this;
        }

        public Builder withIsFavorite(Boolean isFavorite){
            this.isFavorite = isFavorite;
            return this;
        }

        public Builder withPublisher(String publisher){
            this.publisher = publisher;
            return this;
        }
        
    }
}