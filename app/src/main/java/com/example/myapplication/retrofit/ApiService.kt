package com.example.myapplication.retrofit

import com.example.myapplication.model.SearchModel
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @GET(".")
    fun getMovies(
        @Query("apikey") apiKey: String?,
        @Query("s") queryParameter: String
    ): Call<SearchModel>
}