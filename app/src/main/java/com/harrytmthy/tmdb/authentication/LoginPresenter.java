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
import com.harrytmthy.tmdb.authentication.model.LoginContract;
import com.harrytmthy.tmdb.base.BasePresenter;
import com.harrytmthy.tmdb.di.ActivityScope;

import javax.inject.Inject;

import androidx.annotation.VisibleForTesting;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version LoginPresenter, v 0.1 2019-12-17 16:51 by Harry Timothy
 */
@ActivityScope
public class LoginPresenter extends BasePresenter<AuthAction, AuthState> implements LoginContract.Presenter {

    private final CreateSession createSession;

    private final CreateToken createToken;

    private final ValidateToken validateToken;

    private final AuthModelMapper authModelMapper;

    @Getter @Setter private String username;

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
                    if(authState instanceof AuthState.Error) return Observable.just(authState);
                    final TokenParam tokenParam = new TokenParam();
                    tokenParam.setUsername(username);
                    tokenParam.setPassword(password);
                    tokenParam.setRequestToken(((AuthState.Login) authState).data.getRequestToken());
                    return handle(validateToken.execute(tokenParam)).flatMap( state -> {
                        if(state instanceof AuthState.Error) return Observable.just(state);
                        final SessionParam param = new SessionParam();
                        param.setRequestToken(((AuthState.Login) state).data.getRequestToken());
                        return handle(createSession.execute(param));
                    });
                }).startWith(new AuthState.Loading());
            } else if(action instanceof AuthAction.Register) return Observable.just(new AuthState.Register());
            else return Observable.just(new AuthState.Loading());
        });
    }

    private Observable<AuthState> handle(Observable<Auth> useCase) {
        return useCase.map(this.authModelMapper::map)
            .onErrorReturn(AuthState.Error::new);
    }

    @Override
    public void login() {
        doAction(new AuthAction.Login());
    }

    @Override
    public void register() {
        doAction(new AuthAction.Register());
    }

    @SuppressWarnings("unused")
    @Override
    public void setPassword(CharSequence s, int start, int before, int count) {
        this.password = s.toString();
    }

}