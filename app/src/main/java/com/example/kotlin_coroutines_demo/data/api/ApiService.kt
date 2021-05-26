package com.example.kotlin_coroutines_demo.data.api

import com.example.kotlin_coroutines_demo.data.model.ApiUser
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers() : List<ApiUser>

    @GET("more-users")
    suspend fun getMoreUsers() : List<ApiUser>

    @GET("error")
    suspend fun getUsersWithErrors() : List<ApiUser>
}