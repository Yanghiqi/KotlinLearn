package com.example.kotlinlearn.OkHttp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinlearn.R
import com.example.kotlinlearn.databinding.ActivityOkHttpBinding
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class OkHttpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOkHttpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOkHttpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
    }
    private fun fetchData() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://192.168.42.7:9876/image/listImage?page=0&pageSize=100")
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                response.body?.string()?.let { jsonString ->
                    val apiResponse = Gson().fromJson(jsonString, ApiResponse::class.java)
                    runOnUiThread {
                        binding.recyclerView.adapter = ImageAdapter(apiResponse.data)
                    }
                }
            }
        })
    }
}
data class ImageData(
    val createTime: Long,
    val id: Long,
    val name: String,
    val path: String,
    val size: Long
)

data class ApiResponse(
    val data: List<ImageData>,
    val errorCode: Int,
    val isSuccess: Boolean
)

