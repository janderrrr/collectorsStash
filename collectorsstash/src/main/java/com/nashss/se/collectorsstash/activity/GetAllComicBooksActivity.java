package com.nashss.se.collectorsstash.activity;

import com.nashss.se.collectorsstash.activity.request.GetAllComicBooksRequest;
import com.nashss.se.collectorsstash.activity.results.GetAllComicBooksResults;
import com.nashss.se.collectorsstash.converter.ModelConverter;
import com.nashss.se.collectorsstash.dynamodb.ComicBookDao;
import com.nashss.se.collectorsstash.dynamodb.models.ComicBook;
import com.nashss.se.collectorsstash.models.ComicBookModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class GetAllComicBooksActivity {
    private final Logger log = LogManager.getLogger();
    private final ComicBookDao comicDao;

    @Inject
    public GetAllComicBooksActivity(ComicBookDao comicDao){
        this.comicDao = comicDao;
    }

    public GetAllComicBooksResults handleRequest(final GetAllComicBooksRequest getAllComicBooksRequest){
        log.info("Received GetAllComicBooksRequest {}", getAllComicBooksRequest);

        List<ComicBook> comicBookList = comicDao.getAllComicBooks(
                getAllComicBooksRequest.getSeriesTitle(),
                getAllComicBooksRequest.getVolumeNumber()
        );

        List<ComicBookModel> comicBookModels = new ModelConverter().toComicBookModelList(comicBookList);

        String seriesTitle = null;
        String volumeNumber = null;

        if(!comicBookModels.isEmpty()){
            ComicBookModel model = comicBookModels.get(comicBookModels.size() - 1);
            seriesTitle = model.getSeriesTitle();
            volumeNumber = model.getVolumeNumber();
        }

        GetAllComicBooksResults results = GetAllComicBooksResults.builder()
                .withComicList(comicBookModels)
                .withSeriesTitle(seriesTitle)
                .withVolumeNumber(volumeNumber)
                .build();


        log.info("results: {}", results.toString());

        return results;

    }
}