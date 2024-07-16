package com.bitcode.a15_04_24_contentprovidersdemp

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
//    private lateinit var contentResolver: ContentResolver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Log.e("tag", "--------------------------")
        getSystemPreferences()
        Log.e("tag", "--------------------------")
        getSMS()
        Log.e("tag", "--------------------------")
        getCallLog()

        Log.e("tag", "--------------------------")
        addTask(103,"Complete Assignment", 2, false)
        addTask(104,"Complete Tests", 3, false)
        addTask(105,"Appear For Test", 4, true)
        addTask(106, "Meeting", 5, true)

        Log.e("tag", "--------------------------")

        getAllTasks()

        Log.e("tag", "--------------------------")
    }


    private fun addTask(taskId : Int, title : String, priority : Int, isComplete : Boolean){
        var contentUri = Uri.parse("content://in.bitcode.contents")
        contentUri = Uri.withAppendedPath(contentUri, "tasks")

        val values = ContentValues()
        values.put("task_id", taskId)
        values.put("task_title",title)
        values.put("task_priority",priority)
        values.put("task_completion", isComplete)

        val uriToNewTask = contentResolver.insert(contentUri,values)

        Log.e("tag", "${uriToNewTask.toString()}")

        val c = contentResolver.query(uriToNewTask!!, null, null, null)

        while (c!!.moveToNext()){
            Log.e("tag","${c.getInt(0)}")
        }
        c.close()
    }

    private fun getAllTasks(){
        var contentUri = Uri.parse("content://in.bitcode.contents")
        contentUri = Uri.withAppendedPath(contentUri,"tasks")

        val c = contentResolver.query(contentUri,null,null,null)

        while (c!!.moveToNext()){
            Log.e("tag", "${c.getInt(0)}  -- ${c.getString(1)}  -- ${c.getInt(2)} -- ${c.getString(3)}")
        }
        c.close()
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