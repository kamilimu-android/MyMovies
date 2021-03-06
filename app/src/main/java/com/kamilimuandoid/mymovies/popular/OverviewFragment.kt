package com.kamilimuandoid.mymovies.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kamilimuandoid.mymovies.R
import kotlinx.android.synthetic.main.fragment_overview.*

class OverviewFragment : Fragment() {

    private lateinit var viewModel: OverviewViewModel
    private lateinit var movieAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)
        movieAdapter = MoviesAdapter()


        viewModel.status.observe(viewLifecycleOwner, Observer {
            when(it) {
                OverviewViewModel.MovieApiStatus.LOADING -> {
                    tvPlaceHolder.text = "Loading..."
                }
                OverviewViewModel.MovieApiStatus.SUCCESS -> {
                    viewModel.movies.observe(viewLifecycleOwner, Observer {
                        if (it.isNotEmpty()){
                            movieAdapter.updateList(it)
                            recyclerview.adapter = movieAdapter
                        } else {
                            tvPlaceHolder.text = "No Movies found!!"
                        }
                    })
                }
                OverviewViewModel.MovieApiStatus.ERROR -> {
                    tvPlaceHolder.text = "There was an error while fetching the movies!!"
                }
            }
        })
    }

}
