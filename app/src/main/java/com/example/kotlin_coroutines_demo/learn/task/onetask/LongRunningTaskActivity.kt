package com.example.kotlin_coroutines_demo.learn.task.onetask

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_coroutines_demo.R
import com.example.kotlin_coroutines_demo.data.api.ApiHelperImpl
import com.example.kotlin_coroutines_demo.data.api.RetrofitBuilder
import com.example.kotlin_coroutines_demo.data.local.DatabaseBuilder
import com.example.kotlin_coroutines_demo.data.local.DatabaseHelperImpl
import com.example.kotlin_coroutines_demo.utils.Status
import com.example.kotlin_coroutines_demo.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_long_running_task.*

class LongRunningTaskActivity : AppCompatActivity() {

    private lateinit var viewModel: LongRunningTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_long_running_task)

        setUpViewModel()
        setUpViewModelObserver()
    }

    private fun setUpViewModelObserver() {
        viewModel.getStatus().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { string ->
                        textView.text = string
                    }
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    textView.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(this))
            )
        ).get(LongRunningTaskViewModel::class.java)
    }
}