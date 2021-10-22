package com.example.myapplication.model

import java.io.Serializable

data class SearchModel(
    val Response: String,
    val Search: List<Search>?,
    val totalResults: String
) : Serializable