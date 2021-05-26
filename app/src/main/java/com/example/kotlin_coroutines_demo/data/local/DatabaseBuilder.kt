package com.example.kotlin_coroutines_demo.data.local

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context) : AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class){
                INSTANCE = buildRoomDb(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDb(context: Context) = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "UserDatabase"
    ).build()

}