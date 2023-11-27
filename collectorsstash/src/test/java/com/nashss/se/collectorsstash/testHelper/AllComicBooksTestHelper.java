package com.nashss.se.collectorsstash.testHelper;

import com.nashss.se.collectorsstash.dynamodb.models.ComicBook;

import java.util.ArrayList;
import java.util.List;

public class AllComicBooksTestHelper {

    public AllComicBooksTestHelper(){}

    public static ComicBook generateComicBook(int sequenceNum){
        ComicBook comicBook = new ComicBook();
        comicBook.setSeriesTitle("seriesTitle" + sequenceNum);
        comicBook.setVolumeNumber("VolumeOne" + sequenceNum);
        comicBook.setIssueNumber("1" + sequenceNum);
        comicBook.setDate("date" + sequenceNum);
        comicBook.setPrice(sequenceNum);
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
