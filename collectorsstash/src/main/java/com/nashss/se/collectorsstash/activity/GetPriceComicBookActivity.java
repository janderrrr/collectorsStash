package com.nashss.se.collectorsstash.activity;

import com.nashss.se.collectorsstash.activity.request.GetPriceComicBookRequest;
import com.nashss.se.collectorsstash.activity.results.GetPriceComicBookResult;
import com.nashss.se.collectorsstash.converter.ModelConverter;
import com.nashss.se.collectorsstash.dynamodb.ComicBookDao;
import com.nashss.se.collectorsstash.dynamodb.models.ComicBook;
import com.nashss.se.collectorsstash.models.ComicBookModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import javax.inject.Inject;


public class GetPriceComicBookActivity {
    private final Logger log = LogManager.getLogger();
    private final ComicBookDao comicBookDao;

    /**
     * Constructs a new activity for retrieving the price of a comic book.
     *
     * @param comicBookDao The ComicBookDao used for accessing the data store.
     */
    @Inject
    public GetPriceComicBookActivity(ComicBookDao comicBookDao) {
        this.comicBookDao = comicBookDao;
    }

    /**
     * Handles the incoming request to retrieve a list of comic books with prices equal to or greater than
     * the specified price for a given series.
     *
     * <p>This method processes the GetPriceComicBookRequest, extracts the series ID and price information,
     * and calls the ComicBookDao to fetch the relevant comic books. The retrieved comic books are then
     * converted to ComicBookModel instances using a ModelConverter.</p>
     *
     * @param getPriceComicBookRequest The request object containing the series ID and price information.
     * @return GetPriceComicBookResult object containing the API-defined {@link ComicBookModel} list.
     */
    public GetPriceComicBookResult handleRequest(final GetPriceComicBookRequest getPriceComicBookRequest) {
        log.info("Received GetPriceComicBookRequest {}", getPriceComicBookRequest);

        String requestedSeriesId = getPriceComicBookRequest.getSeriesId();
        int requestedPrice = getPriceComicBookRequest.getPrice();

        // Use the new method to get comic books based on the seriesId and price
        List<ComicBook> comicBooks = comicBookDao.getComicByPrice(requestedSeriesId, requestedPrice);

        List<ComicBookModel> sortedComicBookByPrice = new ModelConverter().toComicBookModelList(comicBooks);

        return GetPriceComicBookResult.builder()
                .withComicList(sortedComicBookByPrice)
                .build();
    }
}
