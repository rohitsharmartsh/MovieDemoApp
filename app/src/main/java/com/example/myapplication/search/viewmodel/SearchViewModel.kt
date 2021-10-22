package com.example.myapplication.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.SearchModel
import com.example.myapplication.search.repository.SearchRepository
import com.example.myapplication.utils.Resource
import com.example.myapplication.utils.Event

class SearchViewModel : ViewModel() {
    /**
     * shouldInstantSearchShow variable has been used to show/hide Instant search
     */
    private val _shouldInstantSearchShow = MutableLiveData<Boolean>()
    val shouldInstantSearchShow: LiveData<Boolean> = _shouldInstantSearchShow

    init {
        _shouldInstantSearchShow.value = false
    }

    fun getInstantSearch(queryText: String): LiveData<Event<Resource<SearchModel>>> {
        return SearchRepository.getInstantSearch(queryText)
    }

    fun shouldInstantSearchShow(isInstantSearchShown: Boolean) {
        _shouldInstantSearchShow.value = isInstantSearchShown
    }
}