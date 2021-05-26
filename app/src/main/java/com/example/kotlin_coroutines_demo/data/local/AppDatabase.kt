package com.example.kotlin_coroutines_demo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun userDao() : UserDao

}