package com.example.myapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkUtil {
    fun isConnectionAvailable(con: Context?): Boolean {
        if (con == null) {
            return false
        }
        val connMgr = con.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        var activeNetwork: NetworkInfo? = null
        if (connMgr != null) {
            activeNetwork = connMgr.activeNetworkInfo
        }
        return activeNetwork != null && activeNetwork.isConnected
    }

}