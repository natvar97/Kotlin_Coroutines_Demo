package com.example.kotlin_coroutines_demo.learn.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_coroutines_demo.data.api.ApiHelper
import com.example.kotlin_coroutines_demo.data.local.DatabaseHelper
import com.example.kotlin_coroutines_demo.data.local.UserEntity
import com.example.kotlin_coroutines_demo.utils.Resource
import kotlinx.coroutines.launch

class RoomDatabaseViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val users = MutableLiveData<Resource<List<UserEntity>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            try {
                val userFromDb = dbHelper.getUsers()

                if (userFromDb.isEmpty()) {
                    val usersFromApi = apiHelper.getUsers()
                    val usersToAddDb = mutableListOf<UserEntity>()

                    for (apiUser in usersFromApi) {
                        val user = UserEntity(
                            apiUser.id,
                            apiUser.name,
                            apiUser.email,
                            apiUser.avatar
                        )

                        usersToAddDb.add(user)
                    }

                    dbHelper.insertAll(usersToAddDb)

                    users.postValue(Resource.success(usersToAddDb))
                } else {
                    users.postValue(Resource.success(userFromDb))
                }


            } catch (e : Exception) {
                emptyList<UserEntity>()
            }

        }
    }

    fun getUsers() : LiveData<Resource<List<UserEntity>>> {
        return users
    }

}