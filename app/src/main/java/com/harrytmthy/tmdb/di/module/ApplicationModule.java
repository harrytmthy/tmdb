package com.harrytmthy.tmdb.di.module;

import com.harrytmthy.data.account.mapper.AccountResultMapper;
import com.harrytmthy.data.account.repository.AccountEntityRepository;
import com.harrytmthy.data.account.source.AccountEntityDataFactory;
import com.harrytmthy.data.authentication.mapper.AuthResultMapper;
import com.harrytmthy.data.authentication.repository.AuthEntityRepository;
import com.harrytmthy.data.authentication.source.AuthEntityDataFactory;
import com.harrytmthy.data.executor.JobExecutor;
import com.harrytmthy.data.movie.mapper.MovieResultMapper;
import com.harrytmthy.data.movie.repository.MovieEntityRepository;
import com.harrytmthy.data.movie.source.MovieEntityDataFactory;
import com.harrytmthy.domain.account.repository.AccountRepository;
import com.harrytmthy.domain.authentication.repository.AuthRepository;
import com.harrytmthy.domain.executor.PostExecutionThread;
import com.harrytmthy.domain.executor.ThreadExecutor;
import com.harrytmthy.domain.movie.repository.MovieRepository;
import com.harrytmthy.tmdb.TmdbApplication;
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

    @Binds abstract Application bindApplication(TmdbApplication tmdbApplication);

    @Binds abstract Context bindContext(Application application);

    @Binds abstract ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor);

    @Binds abstract PostExecutionThread providePostExecutionThread(UIThread uiThread);

    @Provides static MovieEntityDataFactory provideMovieEntityDataFactory() {
        return new MovieEntityDataFactory();
    }

    @Provides static MovieEntityRepository provideMovieEntityRepository(
        MovieEntityDataFactory movieEntityDataFactory,
        MovieResultMapper movieResultMapper) {
        return new MovieEntityRepository(movieEntityDataFactory, movieResultMapper);
    }

    @Binds
    abstract MovieRepository provideMovieRepository(MovieEntityRepository movieEntityRepository);

    @Provides static AuthEntityDataFactory provideAuthEntityDataFactory() {
        return new AuthEntityDataFactory();
    }

    @Provides static AuthEntityRepository provideAuthEntityRepository(
        AuthEntityDataFactory authEntityDataFactory,
        AuthResultMapper authResultMapper) {
        return new AuthEntityRepository(authEntityDataFactory, authResultMapper);
    }

    @Binds abstract AuthRepository provideAuthRepository(AuthEntityRepository authEntityRepository);

    @Provides static AccountEntityDataFactory provideAccountEntityDataFactory(Context context) {
        return new AccountEntityDataFactory(context);
    }

    @Provides static AccountEntityRepository provideAccountEntityRepository(
        AccountEntityDataFactory accountEntityDataFactory,
        AccountResultMapper accountResultMapper) {
        return new AccountEntityRepository(accountEntityDataFactory, accountResultMapper);
    }

    @Binds abstract AccountRepository provideAccountRepository(AccountEntityRepository accountEntityRepository);

}