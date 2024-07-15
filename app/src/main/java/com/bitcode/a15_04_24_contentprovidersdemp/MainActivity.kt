package com.bitcode.a15_04_24_contentprovidersdemp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        getSystemPreferences()
        getSMS()
        getCallLog()
    }

    private fun getSMS(){
        val contentUri = Uri.parse("content://sms")
        val c = contentResolver.query(contentUri,
            null,
            null,
            null)
        for (eachColumn in c!!.columnNames){
            Log.e("tag", "$eachColumn")
        }

        c.close()
    }

    private fun getCallLog(){
        val contentUri = android.provider.CallLog.Calls.CONTENT_URI
        val c = contentResolver.query(contentUri,
            null,
            null,
            null)

        for (eachColumn in c!!.columnNames){
            Log.e("tag","$eachColumn")
        }
        c.close()
    }


    private fun getSystemPreferences(){
        val contentUri = android.provider.Settings.System.CONTENT_URI

        val c = contentResolver.query(contentUri,
            null,
            null,
            null)

        for (eachColumn in c!!.columnNames) {
            Log.e("tag", "$eachColumn")
        }
        c.close()
    }
}