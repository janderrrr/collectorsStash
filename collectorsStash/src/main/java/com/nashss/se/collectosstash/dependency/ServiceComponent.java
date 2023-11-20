package com.nashss.se.collectosstash.dependency;

import com.nashss.se.collectosstash.activity.GetAllComicBooksActivity;
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
}
