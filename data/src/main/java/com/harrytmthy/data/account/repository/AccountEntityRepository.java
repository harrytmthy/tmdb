package com.harrytmthy.data.account.repository;

import com.harrytmthy.data.account.mapper.AccountResultMapper;
import com.harrytmthy.data.account.source.AccountEntityDataFactory;
import com.harrytmthy.domain.account.model.FavoriteParam;
import com.harrytmthy.domain.account.model.Status;
import com.harrytmthy.domain.account.repository.AccountRepository;
import com.harrytmthy.domain.movie.model.PagedMovie;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AccountEntityRepository, v 0.1 2019-12-22 21:27 by Harry Timothy
 */
@Singleton
public class AccountEntityRepository implements AccountRepository {

    private final AccountEntityDataFactory accountEntityDataFactory;

    private final AccountResultMapper accountResultMapper;

    @Inject public AccountEntityRepository(AccountEntityDataFactory accountEntityDataFactory,
        AccountResultMapper accountResultMapper) {
        this.accountEntityDataFactory = accountEntityDataFactory;
        this.accountResultMapper = accountResultMapper;
    }

    @Override
    public Observable<Status> markFavorite(FavoriteParam param) {
        return accountEntityDataFactory.createService()
            .markFavorite(param)
            .map(accountResultMapper::map);
    }

    @Override
    public Observable<PagedMovie> getFavoriteMovies(int page) {
        return accountEntityDataFactory.createService()
            .getFavoriteMovies(page)
            .map(accountResultMapper::map);
    }

}