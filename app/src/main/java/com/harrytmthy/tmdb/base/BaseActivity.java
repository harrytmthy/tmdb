package com.harrytmthy.tmdb.base;

import com.harrytmthy.tmdb.R;
import com.harrytmthy.tmdb.authentication.LoginActivity;
import com.harrytmthy.tmdb.constants.AppConstants;

import android.content.Intent;
import android.widget.Toast;

import dagger.android.support.DaggerAppCompatActivity;
import retrofit2.HttpException;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version BaseActivity, v 0.1 2019-12-15 17:49 by Harry Timothy
 */
public abstract class BaseActivity extends DaggerAppCompatActivity {

    protected void handleError(final Throwable error) {
        if (error instanceof HttpException) {
            final int code = ((HttpException) error).code();
            if (code == 401) {
                Toast.makeText(this, getString(R.string.error_http_unauthorized), Toast.LENGTH_SHORT).show();
                startActivityForResult(new Intent(this, LoginActivity.class),
                    AppConstants.ACTIVITY_LOGIN_REQUEST_CODE);
            } else if(code == 404) Toast.makeText(this, getString(R.string.error_http_not_found), Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}