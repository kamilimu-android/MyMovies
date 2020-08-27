package com.kamilimuandoid.mymovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kamilimuandoid.mymovies.R
import com.kamilimuandoid.mymovies.network.MyMoviesRetrofit.retrofitService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movies = retrofitService.getPopularMovies()
        text.text = movies.toString()
    }
}