package com.kamilimuandoid.mymovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kamilimuandoid.mymovies.model.MovieResponse
import com.kamilimuandoid.mymovies.network.MyMoviesRetrofit.retrofitService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
