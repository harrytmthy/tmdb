package com.harrytmthy.tmdb;

import com.harrytmthy.tmdb.common.AppConstants;
import com.harrytmthy.tmdb.movie.MovieActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, MovieActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }, AppConstants.SPLASH_SCREEN_DURATION);
    }

}