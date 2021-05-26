package com.example.kotlin_coroutines_demo.learn.errorhandling.supervisor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_coroutines_demo.data.api.ApiHelper
import com.example.kotlin_coroutines_demo.data.local.DatabaseHelper
import com.example.kotlin_coroutines_demo.data.model.ApiUser
import com.example.kotlin_coroutines_demo.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class IgnoreErrorAndContinueViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val users = MutableLiveData<Resource<List<ApiUser>>>()

    init {
        fetchUser()
    }

    private fun fetchUser() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))

            try {
                supervisorScope {
                    val usersFromApiDeferred = async { apiHelper.getUsers() }
                    val moreUsersFromApiDeferred = async { apiHelper.getMoreUsers() }

                    val usersFromApi = try {
                        usersFromApiDeferred.await()
                    } catch (e: Exception) {
                        emptyList<ApiUser>()
                    }

                    val moreUsersFromApi = try {
                        moreUsersFromApiDeferred.await()
                    } catch (e: Exception) {
                        emptyList<ApiUser>()
                    }

                    val allUsersFromApi = mutableListOf<ApiUser>()
                    allUsersFromApi.addAll(usersFromApi)
                    allUsersFromApi.addAll(moreUsersFromApi)

                    users.postValue(Resource.success(allUsersFromApi))
                }
            } catch (e: Exception) {
                users.postValue(Resource.error(e.message, null))
            }

        }
    }

    fun getUsers(): LiveData<Resource<List<ApiUser>>> {
        return users
    }

}