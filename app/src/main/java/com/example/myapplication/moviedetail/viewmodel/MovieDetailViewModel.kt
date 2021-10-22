package com.example.myapplication.moviedetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Search

class MovieDetailViewModel(search: Search?) : ViewModel() {

    private val _showMovieDetail = MutableLiveData<Search>()
    val showMovieDetail: LiveData<Search> = _showMovieDetail

    init {
        _showMovieDetail.value = search
    }
}