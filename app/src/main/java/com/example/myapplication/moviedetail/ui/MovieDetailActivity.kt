package com.example.myapplication.moviedetail.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMovieDetailBinding
import com.example.myapplication.model.Search
import com.example.myapplication.moviedetail.viewmodel.MovieDetailViewModel
import com.example.myapplication.utils.getViewModel

class MovieDetailActivity : AppCompatActivity() {

    private var dataBinding: ActivityMovieDetailBinding? = null
    private var search: Search? = null
    private val viewModel: MovieDetailViewModel by lazy {
        getViewModel { MovieDetailViewModel(search) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        if (intent.getSerializableExtra("movieDetail") != null) {
            search = intent.getSerializableExtra("movieDetail") as Search
        }
        dataBinding!!.lifecycleOwner = this
        dataBinding!!.viewModel = viewModel


    }
}