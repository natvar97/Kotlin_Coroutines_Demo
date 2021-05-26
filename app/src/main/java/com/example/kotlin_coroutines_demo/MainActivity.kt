package com.example.kotlin_coroutines_demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_coroutines_demo.learn.errorhandling.exceptionhandler.ExceptionHandlerActivity
import com.example.kotlin_coroutines_demo.learn.errorhandling.supervisor.IgnoreErrorAndContinueActivity
import com.example.kotlin_coroutines_demo.learn.retrofit.parallel.ParallelNetworkCallsActivity
import com.example.kotlin_coroutines_demo.learn.retrofit.series.SeriesNetworkCallsActivity
import com.example.kotlin_coroutines_demo.learn.retrofit.single.SingleNetworkCallsActivity
import com.example.kotlin_coroutines_demo.learn.room.RoomDatabaseActivity
import com.example.kotlin_coroutines_demo.learn.task.onetask.LongRunningTaskActivity
import com.example.kotlin_coroutines_demo.learn.task.twotask.TwoLongRunningTaskActivity
import com.example.kotlin_coroutines_demo.learn.timeout.TimeOutActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        singleNetworkCall.setOnClickListener(this)
        seriesNetworkCall.setOnClickListener(this)
        parallelNetworkCall.setOnClickListener(this)
        roomDatabase.setOnClickListener(this)
        timeout.setOnClickListener(this)
        tryCatchError.setOnClickListener(this)
        exceptionHandler.setOnClickListener(this)
        ignoreErrorANdContinue.setOnClickListener(this)
        longRunningTask.setOnClickListener(this)
        twoLongRunningTask.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.singleNetworkCall -> {
                startActivity(Intent(this@MainActivity, SingleNetworkCallsActivity::class.java))
            }
            R.id.seriesNetworkCall -> {
                startActivity(Intent(this@MainActivity, SeriesNetworkCallsActivity::class.java))
            }
            R.id.parallelNetworkCall -> {
                startActivity(Intent(this@MainActivity, ParallelNetworkCallsActivity::class.java))
            }
            R.id.roomDatabase -> {
                startActivity(Intent(this@MainActivity, RoomDatabaseActivity::class.java))
            }
            R.id.timeout -> {
                startActivity(Intent(this@MainActivity, TimeOutActivity::class.java))
            }
            R.id.tryCatchError -> {
                startActivity(Intent(this@MainActivity, ExceptionHandlerActivity::class.java))
            }
            R.id.exceptionHandler -> {
                startActivity(Intent(this@MainActivity, ExceptionHandlerActivity::class.java))
            }
            R.id.ignoreErrorANdContinue -> {
                startActivity(Intent(this@MainActivity, IgnoreErrorAndContinueActivity::class.java))
            }
            R.id.longRunningTask -> {
                startActivity(Intent(this@MainActivity, LongRunningTaskActivity::class.java))
            }
            R.id.twoLongRunningTask -> {
                startActivity(Intent(this@MainActivity, TwoLongRunningTaskActivity::class.java))
            }

        }
    }
}

/*

        fun startSingleNetworkCallActivity(view: View) {
            startActivity(Intent(this@MainActivity, SingleNetworkCallsActivity::class.java))
        }

        fun startSeriesNetworkCallsActivity(view: View) {
            startActivity(Intent(this@MainActivity, SeriesNetworkCallsActivity::class.java))
        }

        fun startParallelNetworkCallsActivity(view: View) {
            startActivity(Intent(this@MainActivity, ParallelNetworkCallsActivity::class.java))
        }

        fun startRoomDatabaseActivity(view: View) {
            startActivity(Intent(this@MainActivity, RoomDatabaseActivity::class.java))
        }

        fun startTimeoutActivity(view: View) {
            startActivity(Intent(this@MainActivity, TimeOutActivity::class.java))
        }

        fun startTryCatchActivity(view: View) {
            startActivity(Intent(this@MainActivity, TryCatchActivity::class.java))
        }

        fun startExceptionHandlerActivity(view: View) {
            startActivity(Intent(this@MainActivity, ExceptionHandlerActivity::class.java))
        }

        fun startIgnoreErrorAndContinueActivity(view: View) {
            startActivity(Intent(this@MainActivity, IgnoreErrorAndContinueActivity::class.java))
        }

        fun startLongRunningTaskActivity(view: View) {
            startActivity(Intent(this@MainActivity, LongRunningTaskActivity::class.java))
        }

        fun startTwoLongRunningTasksActivity(view: View) {
            startActivity(Intent(this@MainActivity, TwoLongRunningTaskActivity::class.java))
        }


 */