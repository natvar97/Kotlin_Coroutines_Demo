package com.example.kotlin_coroutines_demo.data.local

interface DatabaseHelper {

    suspend fun getUsers(): List<UserEntity>

    suspend fun insertAll(users: List<UserEntity>)

}