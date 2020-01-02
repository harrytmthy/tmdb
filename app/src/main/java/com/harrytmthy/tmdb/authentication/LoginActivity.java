package com.harrytmthy.tmdb.authentication;

import com.harrytmthy.data.constants.DataConstants;
import com.harrytmthy.domain.authentication.model.Auth;
import com.harrytmthy.tmdb.R;
import com.harrytmthy.tmdb.authentication.model.AuthState;
import com.harrytmthy.tmdb.authentication.model.LoginContract;
import com.harrytmthy.tmdb.base.BaseActivity;
import com.harrytmthy.tmdb.databinding.ActivityLoginBinding;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import javax.inject.Inject;

import androidx.databinding.DataBindingUtil;
import androidx.preference.PreferenceManager;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version LoginActivity, v 0.1 2019-12-17 18:15 by Harry Timothy
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject LoginContract.Presenter presenter;

    private ActivityLoginBinding binding;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_login);
        binding.setLifecycleOwner(this);
        binding.setPresenter(presenter);

        presenter.bind(this);
        if(getSupportActionBar() == null) return;
        getSupportActionBar().setTitle(getString(R.string.activity_login_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void render(AuthState state) {
        binding.setState(state);
        if(state instanceof AuthState.Login) renderLoginState(((AuthState.Login) state).data);
        else if(state instanceof AuthState.Register) renderRegisterState();
        else if(state instanceof AuthState.Error) renderErrorState(((AuthState.Error) state).error);
    }

    @Override
    public void renderLoginState(Auth auth) {
        PreferenceManager.getDefaultSharedPreferences(this)
            .edit()
            .putString(getString(R.string.key_session_id), auth.getSessionId())
            .apply();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void renderRegisterState() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(DataConstants.URL_REGISTER)));
    }

    @Override
    public void renderErrorState(Throwable error) {
        handleError(error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbind();
    }

}