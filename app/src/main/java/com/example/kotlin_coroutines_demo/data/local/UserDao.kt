package com.example.kotlin_coroutines_demo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<UserEntity>

    @Insert
    suspend fun insertAll(users: List<UserEntity>)

    @Delete
    suspend fun delete(user: UserEntity)

}