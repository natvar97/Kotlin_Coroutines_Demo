package com.example.kotlin_coroutines_demo.data.api

import com.example.kotlin_coroutines_demo.data.model.ApiUser

class ApiHelperImpl(
    private val apiService: ApiService
) : ApiHelper {
    override suspend fun getUsers(): List<ApiUser> = apiService.getUsers()

    override suspend fun getMoreUsers(): List<ApiUser> = apiService.getMoreUsers()

    override suspend fun getUsersWithErrors(): List<ApiUser> = apiService.getUsersWithErrors()

}