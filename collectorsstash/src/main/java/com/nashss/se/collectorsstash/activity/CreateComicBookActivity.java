package com.nashss.se.collectorsstash.activity;

import com.nashss.se.collectorsstash.activity.request.CreateComicBookRequest;
import com.nashss.se.collectorsstash.activity.results.CreateComicBookResult;
import com.nashss.se.collectorsstash.converter.ModelConverter;
import com.nashss.se.collectorsstash.dynamodb.ComicBookDao;
import com.nashss.se.collectorsstash.dynamodb.models.ComicBook;
import com.nashss.se.collectorsstash.models.ComicBookModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateComicBookActivity {
    private final Logger log = LogManager.getLogger();
    private final ComicBookDao comicBookDao;

    /**
     * Instantiates a new CreateComicBookActivity object.
     *
     * @param comicBookDao ComicBookDao to access the comics table.
     */
    @Inject
    public CreateComicBookActivity(ComicBookDao comicBookDao) {
        this.comicBookDao = comicBookDao;
    }


    /**
     * This method handles the incoming request by persisting a new Comic Book
     * with the provided ComicBook customerId, seriesId, title, volumeNumber, issueNumber
     * price, isFavorite, and publisher from the request.
     * <p>
     * It then returns the newly created ComicBook.
     * <p>
     *
     * @param createComicBookRequest request object containing the customerId, seriesId, title, volumeNumber,
     *                               issueNumber, Price,
     *                               publisher, and isFavorite associated with it
     * @return createComicBook result object containing the API defined {@link ComicBookModel}
     */
    public CreateComicBookResult handleRequest(final CreateComicBookRequest createComicBookRequest) {
        log.info("Received CreateComicBookRequest {}", createComicBookRequest);

        ComicBook comicBook = new ComicBook();
        comicBook.setSeriesId(createComicBookRequest.getSeriesId());
        comicBook.setCustomerId(createComicBookRequest.getCustomerId());
        comicBook.setIssueNumber(createComicBookRequest.getIssueNumber());
        comicBook.setTitle(createComicBookRequest.getTitle());
        comicBook.setVolumeNumber(createComicBookRequest.getVolumeNumber());
        comicBook.setFavorite(createComicBookRequest.getFavorite());
        comicBook.setPublisher(createComicBookRequest.getPublisher());
        comicBook.setPrice(createComicBookRequest.getPrice());
        comicBook.setYear(createComicBookRequest.getYear());

        comicBookDao.comicSave(comicBook);

        ComicBookModel comicBookModel = new ModelConverter().toComicModel(comicBook);
        return CreateComicBookResult.builder()
                .withComicBook(comicBookModel)
                .build();
    }
}
