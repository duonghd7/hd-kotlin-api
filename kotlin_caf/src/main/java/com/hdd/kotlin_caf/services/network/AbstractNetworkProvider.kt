package com.hdd.kotlin_caf.services.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

/**
 * Created on 2/27/2018.
 * @author duonghd
 */


abstract class AbstractNetworkProvider(private val context: Context) : NetworkProvider {

    @SuppressLint("MissingPermission")
    override fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
