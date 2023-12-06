package com.nashss.se.collectorsstash.dependency;

import com.nashss.se.collectorsstash.activity.CreateSeriesActivity;
import com.nashss.se.collectorsstash.activity.GetAllComicBooksActivity;
import com.nashss.se.collectorsstash.activity.GetSeriesActivity;
import com.nashss.se.collectorsstash.activity.RemoveSeriesActivity;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return GetAllComicBooksActivity
     */
    GetAllComicBooksActivity provideGetAllComicBooksActivity();

    GetSeriesActivity provideGetSeriesActivity();

    CreateSeriesActivity provideCreateSeriesActivity();

    RemoveSeriesActivity provideRemoveSeriesActivity();
}
