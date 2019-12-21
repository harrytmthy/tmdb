package com.harrytmthy.tmdb.presenter;

import com.harrytmthy.domain.authentication.interactor.CreateSession;
import com.harrytmthy.domain.authentication.interactor.CreateToken;
import com.harrytmthy.domain.authentication.interactor.ValidateToken;
import com.harrytmthy.domain.authentication.model.Auth;
import com.harrytmthy.tmdb.authentication.LoginPresenter;
import com.harrytmthy.tmdb.authentication.LoginView;
import com.harrytmthy.tmdb.authentication.mapper.AuthModelMapper;
import com.harrytmthy.tmdb.authentication.model.AuthState;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version LoginPresenterTest, v 0.1 2019-12-17 18:20 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    private LoginPresenter loginPresenter;

    @Mock private LoginView<AuthState> loginView;

    @Mock private CreateToken createToken;

    @Mock private ValidateToken validateToken;

    @Mock private CreateSession createSession;

    @Mock private AuthModelMapper authModelMapper;

    @Before
    public void setUp() {
        loginPresenter = new LoginPresenter(createSession, createToken, validateToken,
            authModelMapper);
        loginPresenter.bind(loginView);
    }

    @After
    public void tearDown() {
        loginPresenter.unbind();
    }

    @Test
    public void useCase_inLoginPresenter_isExecuted() {
        Auth auth = new Auth();

        given(createToken.execute(null)).willReturn(Observable.just(auth));

        loginPresenter.login();

        verify(createToken).execute(null);
        verifyZeroInteractions(validateToken);
    }

    @Test
    public void register_inLoginPresenter_worksCorrectly() {
        loginPresenter.register();
        verify(loginView).onRegisterClicked();
    }

    @Test
    public void setPassword_inLoginPresenter_worksCorrectly() {
        String password = "test";
        loginPresenter.setPassword(password, 0, 0, 0);
        assertEquals(password, loginPresenter.password);
    }

}