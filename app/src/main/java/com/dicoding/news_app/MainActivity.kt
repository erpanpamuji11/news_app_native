package com.dicoding.news_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.news_app.adapter.NewsAdapter
import com.dicoding.news_app.api.ApiConfig
import com.dicoding.news_app.api.ApiResponse
import com.dicoding.news_app.api.ArticlesItem

import com.dicoding.news_app.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataList: MutableList<ArticlesItem> = mutableListOf()
    private lateinit var myAdapter: NewsAdapter

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myAdapter = NewsAdapter(dataList)

        val layoutManager = LinearLayoutManager(this)
        binding.rvNews.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvNews.addItemDecoration(itemDecoration)
        binding.rvNews.adapter = myAdapter

        findNews()
    }

    private fun findNews() {
        showLoading(true)
        val client = ApiConfig.getApiService().getListData()
        client.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responBody = response.body()
                    if (responBody != null) {
                        dataList.addAll(responBody.articles)
                        myAdapter.notifyDataSetChanged()
                    }
                } else {
                    Log.e(TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure : ${t.message}")
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbNews.visibility = View.VISIBLE
        } else {
            binding.pbNews.visibility = View.GONE
        }
    }
}