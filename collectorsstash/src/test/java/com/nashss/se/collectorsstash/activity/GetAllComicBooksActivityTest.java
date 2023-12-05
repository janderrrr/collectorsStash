package com.nashss.se.collectorsstash.activity;

import com.nashss.se.collectorsstash.activity.request.GetAllComicBooksRequest;
import com.nashss.se.collectorsstash.activity.results.GetAllComicBooksResults;
import com.nashss.se.collectorsstash.dynamodb.ComicBookDao;
import com.nashss.se.collectorsstash.dynamodb.models.ComicBook;
import com.nashss.se.collectorsstash.models.ComicBookModel;
import com.nashss.se.collectorsstash.testHelper.AllComicBooksTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetAllComicBooksActivityTest {
//
//    @Mock
//    private ComicBookDao comicDao;
//    private GetAllComicBooksActivity getAllComicBooksActivity;
//
//
//
//    @BeforeEach
//    void setup(){
//        initMocks(this);
//        getAllComicBooksActivity = new GetAllComicBooksActivity(comicDao);
//    }
//
//    @Test
//    void handleRequest_validNullRequest_returnsComicModelResult(){
//        //GIVEN
//        List<ComicBook> comicList = AllComicBooksTestHelper.generateComicList(4);
//        String startSeries = null;
//        String startVolume = null;
//
//        GetAllComicBooksRequest request = GetAllComicBooksRequest.builder()
//                .withSeriesTitle(startSeries)
//                .withVolumeNumber(startVolume)
//                .build();
//
//        //WHEN
//        when(comicDao.getAllComicBooks(startSeries, startVolume)).thenReturn(comicList);
//        GetAllComicBooksResults results = getAllComicBooksActivity.handleRequest(request);
//
//        //THEN
//        List<ComicBookModel> resultList = results.getComicList();
//
//        for(int i = 0; i < resultList.size(); i++){
//            assertEquals(comicList.get(i).getSeriesTitle(), resultList.get(i).getSeriesTitle());
//        }
//    }
//    @Test
//    void handleRequest_validRequest_returnsComicModelResult() {
//        List<ComicBook> comicList = AllComicBooksTestHelper.generateComicList(4);
//        String startSeries = comicList.get(0).getSeriesTitle();
//        String startVolume = comicList.get(0).getVolumeNumber();
//
//        GetAllComicBooksRequest request = GetAllComicBooksRequest.builder()
//                .withSeriesTitle(startSeries)
//                .withVolumeNumber(startVolume)
//                .build();
//
//        when(comicDao.getAllComicBooks(startSeries, startVolume)).thenReturn(comicList);
//
//        GetAllComicBooksResults results = getAllComicBooksActivity.handleRequest(request);
//
//        List<ComicBookModel> resultsList = results.getComicList();
//
//        for(int i = 0; i < resultsList.size(); i++){
//            assertEquals(comicList.get(i).getSeriesTitle(), resultsList.get(i).getSeriesTitle());
//            assertEquals(comicList.get(i).getVolumeNumber(), resultsList.get(i).getVolumeNumber());
//        }
//    }
}
