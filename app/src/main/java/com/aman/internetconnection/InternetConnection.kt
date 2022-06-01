package com.aman.internetconnection

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log

class InternetConnection() :BroadcastReceiver() {
    override fun onReceive(context: Context?, p1: Intent?) {
        if(connectivityChange != null){
            val connMgr = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connMgr.activeNetworkInfo
            Log.e("TAG"," value ${networkInfo != null && networkInfo.isConnectedOrConnecting}")
            if(networkInfo == null){
                connectivityChange?.OnConnectivityChange(false)
            }else if(networkInfo != null && networkInfo.isConnectedOrConnecting){
            connectivityChange?.OnConnectivityChange( true)}

        }

    }

    companion object{
        var connectivityChange : ConnectivityChange ?= null
    }
}