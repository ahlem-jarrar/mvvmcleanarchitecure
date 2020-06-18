/*
 * *
 *  * Created by Ahlem Jarrar on 3/9/20 8:51 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/9/20 8:51 AM
 *
 *
 */

package com.demo.mvvmcleanarchitecture.utils.connection

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import androidx.lifecycle.LiveData


class ConnectionLiveData(private val context: Context) : LiveData<Boolean>() {

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            intent.extras?.let {
                if (isNetworkConnected()) {
                    postValue(true)

                } else {
                    postValue(false)
                }
            }

        }
    }

    fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val n = cm.activeNetwork

        if (n != null) {
            val nc = cm.getNetworkCapabilities(n)

            return nc.hasTransport(TRANSPORT_CELLULAR) || nc.hasTransport(TRANSPORT_WIFI)
        }

        return false
    }

    override fun onActive() {
        super.onActive()
        val filter = IntentFilter(CONNECTIVITY_ACTION)
        context.registerReceiver(networkReceiver, filter)
    }

    override fun onInactive() {
        super.onInactive()
        context.unregisterReceiver(networkReceiver)
    }
}