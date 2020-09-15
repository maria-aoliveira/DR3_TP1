package com.example.maria_oliveira_dr3_tp1.Database

import android.content.Context
import androidx.room.Room

class AppDatabaseService {

    companion object{
        var appdataBase : AppDatabase? = null
        val database_name = "appMedicoDatabase.sql"
        fun getInstance(context: Context) : AppDatabase {
            if (appdataBase == null) {
                appdataBase = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    database_name
                )
                    .build()
            }
            return appdataBase as AppDatabase
        }
    }
}