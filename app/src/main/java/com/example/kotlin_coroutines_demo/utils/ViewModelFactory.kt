package com.example.kotlin_coroutines_demo.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_coroutines_demo.data.api.ApiHelper
import com.example.kotlin_coroutines_demo.data.local.DatabaseHelper
import com.example.kotlin_coroutines_demo.learn.room.RoomDatabaseViewModel
import com.example.kotlin_coroutines_demo.learn.errorhandling.exceptionhandler.ExceptionHandlerViewModel
import com.example.kotlin_coroutines_demo.learn.errorhandling.supervisor.IgnoreErrorAndContinueViewModel
import com.example.kotlin_coroutines_demo.learn.errorhandling.trycatch.TryCatchViewModel
import com.example.kotlin_coroutines_demo.learn.retrofit.parallel.ParallelNetworkCallsViewModel
import com.example.kotlin_coroutines_demo.learn.retrofit.series.SeriesNetworkCallsViewModel
import com.example.kotlin_coroutines_demo.learn.retrofit.single.SingleNetworkCallsViewModel
import com.example.kotlin_coroutines_demo.learn.task.onetask.LongRunningTaskViewModel
import com.example.kotlin_coroutines_demo.learn.task.twotask.TwoLongRunningTaskViewModel
import com.example.kotlin_coroutines_demo.learn.timeout.TimeOutViewModel

class ViewModelFactory(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExceptionHandlerViewModel::class.java)) {
            return ExceptionHandlerViewModel(apiHelper, dbHelper) as T
        }

        if (modelClass.isAssignableFrom(IgnoreErrorAndContinueViewModel::class.java)) {
            return IgnoreErrorAndContinueViewModel(apiHelper, dbHelper) as T
        }

        if (modelClass.isAssignableFrom(TryCatchViewModel::class.java)) {
            return TryCatchViewModel(apiHelper, dbHelper) as T
        }

        if (modelClass.isAssignableFrom(ParallelNetworkCallsViewModel::class.java)) {
            return ParallelNetworkCallsViewModel(apiHelper, dbHelper) as T
        }

        if (modelClass.isAssignableFrom(SeriesNetworkCallsViewModel::class.java)) {
            return SeriesNetworkCallsViewModel(apiHelper, dbHelper) as T
        }

        if (modelClass.isAssignableFrom(SingleNetworkCallsViewModel::class.java)) {
            return SingleNetworkCallsViewModel(apiHelper, dbHelper) as T
        }

        if (modelClass.isAssignableFrom(RoomDatabaseViewModel::class.java)) {
            return RoomDatabaseViewModel(apiHelper, dbHelper) as T
        }

        if (modelClass.isAssignableFrom(LongRunningTaskViewModel::class.java)) {
            return LongRunningTaskViewModel(apiHelper, dbHelper) as T
        }

        if (modelClass.isAssignableFrom(TwoLongRunningTaskViewModel::class.java)) {
            return TwoLongRunningTaskViewModel(apiHelper, dbHelper) as T
        }

        if (modelClass.isAssignableFrom(TimeOutViewModel::class.java)) {
            return TimeOutViewModel(apiHelper, dbHelper) as T
        }

        throw IllegalArgumentException("Unknown View Model class found")
    }
}