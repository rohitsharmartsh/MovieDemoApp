package com.example.myapplication.utils

/**
 * This class is responsible to communicate the current state of network call to the UI layer
 *
 * @author: Rohit Sharma 30 Jan 2021
 */
data class Resource<out T>(val status: APIStatus, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(APIStatus.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(APIStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(APIStatus.LOADING, data, null)
        }

    }

}