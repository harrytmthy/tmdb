package com.harrytmthy.tmdb.di.module;

import com.harrytmthy.data.executor.JobExecutor;
import com.harrytmthy.data.movie.mapper.MovieResultMapper;
import com.harrytmthy.data.movie.repository.MovieEntityRepository;
import com.harrytmthy.data.movie.source.MovieEntityDataFactory;
import com.harrytmthy.domain.executor.PostExecutionThread;
import com.harrytmthy.domain.executor.ThreadExecutor;
import com.harrytmthy.domain.movie.repository.MovieRepository;
import com.harrytmthy.tmdb.executor.UIThread;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version ApplicationModule, v 0.1 2019-12-12 15:52 by Harry Timothy
 */
@Module
public abstract class ApplicationModule {

    @Binds abstract Context bindContext(Application application);

    @Binds abstract ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor);

    @Binds abstract PostExecutionThread providePostExecutionThread(UIThread uiThread);

    @Provides
    static MovieEntityDataFactory provideMovieEntityDataFactory() {
        return new MovieEntityDataFactory();
    }

    @Provides
    static MovieEntityRepository provideMovieEntityRepository(
        MovieEntityDataFactory movieEntityDataFactory,
        MovieResultMapper movieResultMapper) {
        return new MovieEntityRepository(movieEntityDataFactory, movieResultMapper);
    }

    @Binds
    abstract MovieRepository provideMovieRepository(MovieEntityRepository movieEntityRepository);

}