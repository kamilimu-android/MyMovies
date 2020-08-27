package com.kamilimuandoid.mymovies.network
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kamilimuandoid.mymovies.model.Movie
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface MyMoviesService {

    @GET("/movie/popular")
    fun getPopularMovies(): Deferred<List<Movie>>


}
// Configuring retrofit
private const val BASE_URL = "https://api.themoviedb.org/3"

/**
 * Configure Moshi
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()



/**
 * Configuring retrofit
 */
private val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


object MyMoviesRetrofit {
    val retrofitService : MyMoviesService by lazy {
        retrofit.create(MyMoviesService::class.java)
    }
}
