package com.kamilimuandoid.mymovies.popular

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kamilimuandoid.mymovies.R
import com.kamilimuandoid.mymovies.model.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(){

    private var movies = mutableListOf<Movie>()

    fun updateList(list: List<Movie>) {
        movies = list.toMutableList()
    }

    class MoviesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val BASE_URL = "https://api.themoviedb.org/3"

        fun bind(item: Movie) {
            view.tvOverview.text = item.overview
            view.tvDateValue.text = item.release_date
            Glide.with(view)
                .load("https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080")
                .into(view.ivPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.movie_item, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies.get(position)
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size
}