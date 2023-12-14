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

    @Mock
    private ComicBookDao comicDao;
    private GetAllComicBooksActivity getAllComicBooksActivity;



    @BeforeEach
    void setup(){
        initMocks(this);
        getAllComicBooksActivity = new GetAllComicBooksActivity(comicDao);
    }

    @Test
    void handleRequest_validRequest_returnsComicModelResult() {
        List<ComicBook> comicList = AllComicBooksTestHelper.generateComicList(4);
        String startSeriesId = comicList.get(0).getSeriesId();

        GetAllComicBooksRequest request = GetAllComicBooksRequest.builder()
                .withSeriesId(startSeriesId)
                .build();

        when(comicDao.getAllComicBooks(startSeriesId)).thenReturn(comicList);

        GetAllComicBooksResults results = getAllComicBooksActivity.handleRequest(request);

        List<ComicBookModel> resultsList = results.getComicList();

        for(int i = 0; i < resultsList.size(); i++){
            assertEquals(comicList.get(i).getSeriesId(), resultsList.get(i).getSeriesId());
        }
    }
}
