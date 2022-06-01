package com.aman.internetconnection

import android.app.AlertDialog
import android.app.Dialog
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(), ConnectivityChange {
    private val TAG = "MainActivity"
    lateinit var dialog: AlertDialog
    lateinit var dialogBuilder: AlertDialog.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.apply {
            setTitle(resources.getString(R.string.alert))
            setMessage(resources.getString(R.string.no_internet_connection))
            setCancelable(false)
        }
        dialog = dialogBuilder.create()
        dialog.setCancelable(false)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(InternetConnection(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        InternetConnection.connectivityChange = this

    }

    override fun OnConnectivityChange(isChange: Boolean) {
        Log.e(TAG," in connection $isChange")
        if(!isChange){
            if(!dialog.isShowing){
                dialog.show()
            }
        }else{
            dialog.dismiss()
        }
    }
}