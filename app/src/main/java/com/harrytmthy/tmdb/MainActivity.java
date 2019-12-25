package com.harrytmthy.tmdb;

import com.harrytmthy.tmdb.constants.AppConstants;
import com.harrytmthy.tmdb.movie.list.MovieActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() ->
            startActivity(new Intent(this, MovieActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
            ), AppConstants.SPLASH_SCREEN_DURATION);
    }

}