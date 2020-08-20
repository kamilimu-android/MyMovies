package com.kamilimuandoid.mymovies
import com.kamilimuandoid.mymovies.MovieDetails
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader
import retrofit2.http.GET
import retrofit2.http.Path


interface mymoviesretrofit {

    //https://api.themoviedb.org/3/
    //https://api.themoviedb.org/3/movie/550?api_key=393c2fda43be9037ff54b6a83e751d2d
    @GET("movie/{movie_id")
    fun getMovieDetails(@Path ("movie_id") id: Int) : XsiNilLoader.Single<MovieDetails>
}
// I could not get to reference the single with io.reactivex.Single