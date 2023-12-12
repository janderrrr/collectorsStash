//package com.nashss.se.collectorsstash.activity;
//
//import com.nashss.se.collectorsstash.activity.request.GetPriceComicBookRequest;
//import com.nashss.se.collectorsstash.activity.results.GetPriceComicBookResult;
//import com.nashss.se.collectorsstash.dynamodb.ComicBookDao;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.inject.Inject;
//
//public class GetPriceComicBookActivity {
//    private final Logger log = LogManager.getLogger();
//    private final ComicBookDao comicBookDao;
//
//    @Inject
//    public GetPriceComicBookActivity(ComicBookDao comicBookDao) {
//        this.comicBookDao = comicBookDao;
//    }
//
//    public GetPriceComicBookResult handleRequest(final GetPriceComicBookRequest getPriceComicBookRequest) {
//        log.info("Received GetPriceComicBookRequest {}", getPriceComicBookRequest);
//    }
//}
