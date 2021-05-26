package com.example.kotlin_coroutines_demo.data.api

import com.example.kotlin_coroutines_demo.data.model.ApiUser

interface ApiHelper {

    suspend fun getUsers(): List<ApiUser>

    suspend fun getMoreUsers(): List<ApiUser>

    suspend fun getUsersWithErrors(): List<ApiUser>

}