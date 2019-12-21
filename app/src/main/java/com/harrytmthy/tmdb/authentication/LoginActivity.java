package com.harrytmthy.tmdb.authentication;

import com.harrytmthy.data.constants.DataConstants;
import com.harrytmthy.domain.authentication.model.Auth;
import com.harrytmthy.tmdb.R;
import com.harrytmthy.tmdb.authentication.model.AuthState;
import com.harrytmthy.tmdb.base.BaseActivity;
import com.harrytmthy.tmdb.databinding.ActivityLoginBinding;
import com.harrytmthy.tmdb.movie.list.MovieActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import androidx.databinding.DataBindingUtil;
import androidx.preference.PreferenceManager;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version LoginActivity, v 0.1 2019-12-17 18:15 by Harry Timothy
 */
public class LoginActivity extends BaseActivity implements LoginView<AuthState> {

    @Inject LoginPresenter presenter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActivityLoginBinding binding = DataBindingUtil.setContentView(this,
            R.layout.activity_login);
        binding.setLifecycleOwner(this);
        binding.setPresenter(presenter);

        presenter.bind(this);
    }

    @Override
    public void render(AuthState state) {
        if(state instanceof AuthState.Data) renderDataState(((AuthState.Data) state).data);
        if(state instanceof AuthState.Error) renderErrorState(((AuthState.Error) state).error);
    }

    @Override
    public void renderDataState(Auth auth) {
        PreferenceManager.getDefaultSharedPreferences(this)
            .edit()
            .putString(getString(R.string.key_session_id), auth.getSessionId())
            .apply();
        startActivity(new Intent(this, MovieActivity.class)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    @Override
    public void renderErrorState(Throwable error) {
        Toast.makeText(this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterClicked() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(DataConstants.URL_REGISTER)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbind();
    }

}