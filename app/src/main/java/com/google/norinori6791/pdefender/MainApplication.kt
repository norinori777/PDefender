package com.google.norinori6791.pdefender

import android.app.Application
import android.database.sqlite.SQLiteOpenHelper
import com.google.norinori6791.pdefender.util.DatabaseHelper

class MainApplication : Application() {
    companion object {
        lateinit var helper: SQLiteOpenHelper private set
    }

    override fun onCreate(){
        super.onCreate()
        helper = DatabaseHelper(this@MainApplication)
    }

    override fun onTerminate() {
        helper.close()
        super.onTerminate()
    }
}