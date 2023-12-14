package com.nashss.se.collectorsstash.testHelper;

import com.nashss.se.collectorsstash.dynamodb.models.ComicBook;

import java.util.ArrayList;
import java.util.List;

public class AllComicBooksTestHelper {


    public AllComicBooksTestHelper(){}

    public static ComicBook generateComicBook(int sequenceNum){
        ComicBook comicBook = new ComicBook();
        comicBook.setSeriesId("x12345" + sequenceNum);
        comicBook.setVolumeNumber("VolumeOne" + sequenceNum);
        comicBook.setTitle("title" + 1);
        comicBook.setFavorite(true);
        comicBook.setIssueNumber("1" + sequenceNum);
        comicBook.setYear("1964");
        comicBook.setPrice(50 + sequenceNum);
        comicBook.setPublisher("publisher" + sequenceNum);

        return comicBook;
    }

    public static List<ComicBook> generateComicList(int numOfEvents){
        List<ComicBook> comicBooks = new ArrayList<>();

        for(int i = 0; i < numOfEvents; i++){
            comicBooks.add(generateComicBook(i));
        }
        return comicBooks;
    }

}
