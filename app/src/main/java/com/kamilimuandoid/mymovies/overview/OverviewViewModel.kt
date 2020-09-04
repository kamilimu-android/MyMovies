package com.kamilimuandoid.mymovies.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamilimuandoid.mymovies.BuildConfig
import com.kamilimuandoid.mymovies.model.Movie
import com.kamilimuandoid.mymovies.network.MyMoviesRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.ArrayList

class OverviewViewModel : ViewModel() {

    // Defining the a Coroutine Job and a CoroutineScope using the Main Dispatcher
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // The internal MutableLiveData String that stores the status of the most recent request
    // And the external immutable LiveData for accessing the request status String
    private val _status = MutableLiveData<MovieApiStatus>()
    val status: LiveData<MovieApiStatus>
        get() = _status

    // The internal MutableLiveData that stores the data from the network request
    // And the external immutable LiveData for accessing the request data
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    init {
        fetchPopularMovies()
    }

    /**
     * Using the retrofitService object to request for popular movies from the server
     * The Retrofit service returns a coroutine Deferred, which we await to get the result of the transaction.
     */
    private fun fetchPopularMovies() {
        coroutineScope.launch {
            // Get the Deferred object for our Retrofit request
            val getPopularMovies = MyMoviesRetrofit.retrofitService.getPopularMoviesAsync(
                BuildConfig.API_KEY
            )
            try {
                _status.value = MovieApiStatus.LOADING

                // this will run on a thread managed by Retrofit
                val response = getPopularMovies.await()

                _status.value = MovieApiStatus.SUCCESS

                if(response.results.isNotEmpty()) {
                    _movies.postValue(response.results)
                }
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR

                _movies.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    enum class MovieApiStatus {
        LOADING, ERROR, SUCCESS
    }
}