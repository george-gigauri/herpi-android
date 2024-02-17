package com.gigauri.reptiledb.module.common.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build

class NetworkMonitor(
    context: Context
) {

    private var onAvailable: () -> Unit = { }
    private var onNotAvailable: () -> Unit = { }

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val callBack = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            onAvailable()
        }

        override fun onLosing(network: Network, maxMsToLive: Int) {
            onNotAvailable()
        }

        override fun onLost(network: Network) {
            onNotAvailable()
        }

        override fun onUnavailable() {
            onNotAvailable()
        }
    }

    @SuppressLint("MissingPermission")
    fun isNetworkAvailable(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    @SuppressLint("MissingPermission")
    fun registerNetworkCallback() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(callBack)
        } else {
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(), callBack)
        }
    }

    fun unregisterNetworkCallback() {
        connectivityManager.unregisterNetworkCallback(callBack)
    }

    fun onOnline(onOnline: () -> Unit) {
        this.onAvailable = onOnline
    }

    fun onOffline(onOffline: () -> Unit) {
        this.onNotAvailable = onOffline
    }
}