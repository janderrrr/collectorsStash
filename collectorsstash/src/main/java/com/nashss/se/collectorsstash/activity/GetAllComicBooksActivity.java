package com.nashss.se.collectorsstash.activity;

import com.nashss.se.collectorsstash.activity.request.GetAllComicBooksRequest;
import com.nashss.se.collectorsstash.activity.results.GetAllComicBooksResults;
import com.nashss.se.collectorsstash.converter.ModelConverter;
import com.nashss.se.collectorsstash.dynamodb.ComicBookDao;
import com.nashss.se.collectorsstash.dynamodb.models.ComicBook;
import com.nashss.se.collectorsstash.models.ComicBookModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import javax.inject.Inject;

public class GetAllComicBooksActivity {
    private final Logger log = LogManager.getLogger();
    private final ComicBookDao comicDao;
    /**
     * Instantiates a new GetAllComicBooksActivity object.
     *
     * @param comicDao ComicBookDao to access the Comics table.
     */
    @Inject
    public GetAllComicBooksActivity(ComicBookDao comicDao) {
        this.comicDao = comicDao;
    }
    /**
     * This method handles the incoming request by retrieving the ComicBook from the database.
     * <p>
     * It then returns the Comic Book list.
     * <p>
     *
     * @param getAllComicBooksRequest request object containing the seriesID
     * @return getAllComicBookResult result object containing the Comicbook's List of
     * API defined {@link com.nashss.se.collectorsstash.models.ComicBookModel}s
     */
    public GetAllComicBooksResults handleRequest(final GetAllComicBooksRequest getAllComicBooksRequest) {
        log.info("Received GetAllComicBooksRequest {}", getAllComicBooksRequest);

        String requestedSeriesId = getAllComicBooksRequest.getSeriesId();

        List<ComicBook> comicBooks = comicDao.getAllComicBooks(requestedSeriesId);

        List<ComicBookModel> comicModels = new ModelConverter().toComicBookModelList(comicBooks);

        return GetAllComicBooksResults.builder()
                .withComicList(comicModels)
                .build();

    }
}
