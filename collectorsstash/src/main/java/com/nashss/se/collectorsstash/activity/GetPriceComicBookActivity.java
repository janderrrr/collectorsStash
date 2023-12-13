package com.nashss.se.collectorsstash.activity;

import com.nashss.se.collectorsstash.activity.request.GetPriceComicBookRequest;
import com.nashss.se.collectorsstash.activity.results.GetPriceComicBookResult;
import com.nashss.se.collectorsstash.converter.ModelConverter;
import com.nashss.se.collectorsstash.dynamodb.ComicBookDao;
import com.nashss.se.collectorsstash.dynamodb.models.ComicBook;
import com.nashss.se.collectorsstash.models.ComicBookModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class GetPriceComicBookActivity {
    private final Logger log = LogManager.getLogger();
    private final ComicBookDao comicBookDao;

    @Inject
    public GetPriceComicBookActivity(ComicBookDao comicBookDao) {
        this.comicBookDao = comicBookDao;
    }

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
