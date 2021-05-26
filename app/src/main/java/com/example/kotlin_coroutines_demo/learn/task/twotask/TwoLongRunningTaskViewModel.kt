package com.example.kotlin_coroutines_demo.learn.task.twotask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_coroutines_demo.data.api.ApiHelper
import com.example.kotlin_coroutines_demo.data.local.DatabaseHelper
import com.example.kotlin_coroutines_demo.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TwoLongRunningTaskViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val status = MutableLiveData<Resource<String>>()

    fun startLongRunningTask() {
        viewModelScope.launch {
            status.postValue(Resource.loading(null))

            try {
                val resultOneDeferred = async { doLongRunningTaskOne() }
                val resultTwoDeferred = async { doLongRunningTaskTwo() }

                val resultDeferred = resultOneDeferred.await() + resultTwoDeferred.await()
                status.postValue(Resource.success(resultDeferred))

            } catch (e: Exception) {
                status.postValue(Resource.error("Error in loading status", null))
            }
        }
    }


    private suspend fun doLongRunningTaskOne(): String {
        delay(2000)
        return "One"
    }

    private suspend fun doLongRunningTaskTwo(): String {
        delay(2000)
        return "Two"
    }

    fun getStatus(): LiveData<Resource<String>> {
        return status
    }

}