package com.harrytmthy.tmdb.authentication;

import com.harrytmthy.domain.authentication.interactor.CreateSession;
import com.harrytmthy.domain.authentication.interactor.CreateToken;
import com.harrytmthy.domain.authentication.interactor.ValidateToken;
import com.harrytmthy.domain.authentication.model.Auth;
import com.harrytmthy.domain.authentication.model.SessionParam;
import com.harrytmthy.domain.authentication.model.TokenParam;
import com.harrytmthy.tmdb.authentication.mapper.AuthModelMapper;
import com.harrytmthy.tmdb.authentication.model.AuthAction;
import com.harrytmthy.tmdb.authentication.model.AuthState;
import com.harrytmthy.tmdb.base.BasePresenter;
import com.harrytmthy.tmdb.di.ActivityScope;

import javax.inject.Inject;

import androidx.annotation.VisibleForTesting;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version LoginPresenter, v 0.1 2019-12-17 16:51 by Harry Timothy
 */
@ActivityScope
public class LoginPresenter extends BasePresenter<AuthAction, AuthState> {

    private final CreateSession createSession;

    private final CreateToken createToken;

    private final ValidateToken validateToken;

    private final AuthModelMapper authModelMapper;

    public String username;

    @VisibleForTesting public String password;

    @Inject public LoginPresenter(CreateSession createSession, CreateToken createToken,
        ValidateToken validateToken, AuthModelMapper authModelMapper) {
        this.createSession = createSession;
        this.createToken = createToken;
        this.validateToken = validateToken;
        this.authModelMapper = authModelMapper;
    }

    @Override
    protected ObservableTransformer<AuthAction, AuthState> dispatch() {
        return actionObservable -> actionObservable.switchMap( action -> {
            if(action instanceof AuthAction.Login) {
                return handle(createToken.execute(null)).flatMap(authState -> {
                    final TokenParam tokenParam = new TokenParam();
                    tokenParam.setUsername(username);
                    tokenParam.setPassword(password);
                    tokenParam.setRequestToken(((AuthState.Data) authState).data.getRequestToken());
                    return handle(validateToken.execute(tokenParam)).flatMap( state -> {
                        final SessionParam param = new SessionParam();
                        param.setRequestToken(((AuthState.Data) state).data.getRequestToken());
                        return handle(createSession.execute(param));
                    });
                }).startWith(new AuthState.Loading());
            } else return Observable.just(new AuthState.Loading());
        });
    }

    private Observable<AuthState> handle(Observable<Auth> useCase) {
        return useCase.map(this.authModelMapper::mapToDataState)
            .onErrorReturn(AuthState.Error::new);
    }

    public void login() {
        doAction(new AuthAction.Login());
    }

    public void register() {
        ((LoginView) this.view).onRegisterClicked();
    }

    @SuppressWarnings("unused")
    public void setPassword(CharSequence s, int start, int before, int count) {
        this.password = s.toString();
    }

}