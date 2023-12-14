package com.nashss.se.collectorsstash.dependency;

import com.nashss.se.collectorsstash.activity.CreateComicBookActivity;
import com.nashss.se.collectorsstash.activity.CreateSeriesActivity;
import com.nashss.se.collectorsstash.activity.GetAllComicBooksActivity;
import com.nashss.se.collectorsstash.activity.GetPriceComicBookActivity;
import com.nashss.se.collectorsstash.activity.GetSeriesActivity;
import com.nashss.se.collectorsstash.activity.RemoveSeriesActivity;
import com.nashss.se.collectorsstash.activity.UpdateSeriesActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity for retrieving all comic books.
     *
     * @return GetAllComicBooksActivity instance.
     */
    GetAllComicBooksActivity provideGetAllComicBooksActivity();

    /**
     * Provides the relevant activity for retrieving series information.
     *
     * @return GetSeriesActivity instance.
     */
    GetSeriesActivity provideGetSeriesActivity();

    /**
     * Provides the relevant activity for creating a new series.
     *
     * @return CreateSeriesActivity instance.
     */
    CreateSeriesActivity provideCreateSeriesActivity();

    /**
     * Provides the relevant activity for removing a series.
     *
     * @return RemoveSeriesActivity instance.
     */
    RemoveSeriesActivity provideRemoveSeriesActivity();

    /**
     * Provides the relevant activity for updating a series.
     *
     * @return UpdateSeriesActivity instance.
     */
    UpdateSeriesActivity provideUpdateSeriesActivity();

    /**
     * Provides the relevant activity for creating a new comic book.
     *
     * @return CreateComicBookActivity instance.
     */
    CreateComicBookActivity provideCreateComicBookActivity();

    /**
     * Provides the relevant activity for retrieving the price of a comic book.
     *
     * @return GetPriceComicBookActivity instance.
     */
    GetPriceComicBookActivity provideGetPriceComicBookActivity();
}
