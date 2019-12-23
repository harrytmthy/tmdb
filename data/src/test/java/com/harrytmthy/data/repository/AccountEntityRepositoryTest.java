package com.harrytmthy.data.repository;

import com.harrytmthy.data.account.mapper.AccountResultMapper;
import com.harrytmthy.data.account.model.StatusResult;
import com.harrytmthy.data.account.repository.AccountEntityRepository;
import com.harrytmthy.data.account.source.AccountEntityData;
import com.harrytmthy.data.account.source.AccountEntityDataFactory;
import com.harrytmthy.data.common.PagedResult;
import com.harrytmthy.data.constants.DataConstants;
import com.harrytmthy.data.movie.model.MovieResult;
import com.harrytmthy.domain.account.model.FavoriteParam;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AccountEntityRepositoryTest, v 0.1 2019-12-22 23:12 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountEntityRepositoryTest {

    private AccountEntityRepository accountEntityRepository;

    @Mock
    private AccountEntityDataFactory accountEntityDataFactory;

    @Mock private AccountResultMapper accountResultMapper;

    @Mock private AccountEntityData accountEntityData;

    @Before
    public void setUp() {
        accountEntityRepository = new AccountEntityRepository(accountEntityDataFactory,
            accountResultMapper);
        given(accountEntityDataFactory.createService()).willReturn(accountEntityData);
    }

    @Test
    public void getFavoriteMovies_inAccountEntityRepository_isCalled() {
        PagedResult<MovieResult> pagedResult = new PagedResult<>();
        given(accountEntityData.getFavoriteMovies(1)).willReturn(Observable.just(pagedResult));

        accountEntityRepository.getFavoriteMovies(1);

        verify(accountEntityDataFactory).createService();
        verify(accountEntityData).getFavoriteMovies(1);
    }

    @Test
    public void markFavorite_inAccountEntityRepository_isCalled() {
        StatusResult result = new StatusResult();
        FavoriteParam param = new FavoriteParam();
        param.setFavorite(true);
        param.setMediaId(123);
        param.setMediaType(DataConstants.DEFAULT_MEDIA_TYPE);

        given(accountEntityData.markFavorite(param)).willReturn(Observable.just(result));
        accountEntityRepository.markFavorite(param);

        verify(accountEntityDataFactory).createService();
        verify(accountEntityData).markFavorite(param);
    }

}
