package com.example.myapplication.search.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.SearchModel
import com.example.myapplication.retrofit.ApiService
import com.example.myapplication.retrofit.RetrofitHelper
import com.example.myapplication.utils.Resource
import com.example.myapplication.utils.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SearchRepository {


    private val _SearchModel = MutableLiveData<Event<Resource<SearchModel>>>()
    /*
    Api to call instant search
   */
    fun getInstantSearch(queryText: String): LiveData<Event<Resource<SearchModel>>> {
        val retrofit = RetrofitHelper.getRetrofit()
        val helper = retrofit.create(ApiService::class.java)
        val call = helper.getMovies("9278e7bc", queryText)
        call.enqueue(object : Callback<SearchModel> {
            override fun onResponse(call: Call<SearchModel>, response: Response<SearchModel>) {
                try {
                    if (response.isSuccessful) {
                        _SearchModel.postValue(Event(Resource.success(response.body())))
                    } else {
                        _SearchModel.value = Event(Resource.error(response.errorBody().toString(), null))
                    }
                } catch (e: Exception) {
                    _SearchModel.value = Event(Resource.error(e.message!!, null))
                }
            }

            override fun onFailure(call: Call<SearchModel>, t: Throwable) {
                _SearchModel.value = Event(Resource.error("", null))
            }
        })
        return _SearchModel
    }
}