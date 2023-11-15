package com.nashss.se.collectosstash.activity;

import com.nashss.se.collectosstash.activity.request.GetAllComicBooksRequest;
import com.nashss.se.collectosstash.activity.results.GetAllComicBooksResults;
import com.nashss.se.collectosstash.dynamodb.ComicBookDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetAllComicBooksActivity {
    private final Logger log = LogManager.getLogger();
    private final ComicBookDao comicDao;

    @Inject
    public GetAllComicBooksActivity(ComicBookDao comicDao){
        this.comicDao = comicDao;
    }

    public GetAllComicBooksResults handleRequest(final GetAllComicBooksRequest getAllComicBooksRequest){
        log.info("Received GetAllComicBooksRequest {}", getAllComicBooksRequest);

        String seriesTitle = getAllComicBooksRequest.getSeriesTitle();
        String volumeNumber = getAllComicBooksRequest.getVolumeNumber();
    }
}
