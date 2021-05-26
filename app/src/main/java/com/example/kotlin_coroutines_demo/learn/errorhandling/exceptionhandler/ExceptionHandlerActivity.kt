package com.example.kotlin_coroutines_demo.learn.errorhandling.exceptionhandler

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_coroutines_demo.R
import com.example.kotlin_coroutines_demo.data.api.ApiHelperImpl
import com.example.kotlin_coroutines_demo.data.api.RetrofitBuilder
import com.example.kotlin_coroutines_demo.data.local.DatabaseBuilder
import com.example.kotlin_coroutines_demo.data.local.DatabaseHelperImpl
import com.example.kotlin_coroutines_demo.learn.base.ApiUserAdapter
import com.example.kotlin_coroutines_demo.utils.Status
import com.example.kotlin_coroutines_demo.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_recycle_view.*

class ExceptionHandlerActivity : AppCompatActivity() {

    private lateinit var viewModel: ExceptionHandlerViewModel
    private lateinit var adapter: ApiUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)

        setUpUI()
        setUpViewModel()
        setUpObserver()

    }

    private fun setUpUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ApiUserAdapter(arrayListOf())
        recyclerView.adapter = adapter
    }

    private fun setUpObserver() {
        viewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { list ->
                        adapter.addData(list)
                    }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
            }
        })
    }


    private fun setUpViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(this))
            )
        ).get(ExceptionHandlerViewModel::class.java)
    }
}