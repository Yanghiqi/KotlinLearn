package com.example.kotlinlearn.RecyclerView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlearn.R
import java.util.Random

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val recyclerView: RecyclerView = this.findViewById(R.id.recyclerView)

        // 初始化Random实例
        val random = Random()

        // 创建数据列表并插入100个随机数据
        val itemList = mutableListOf<Item>()
        for (i in 1..100) {
            val title = "Title ${i} - ${randomString(random, 5)}"
            val description = "Description ${i} - ${randomString(random, 10)}"
            itemList.add(Item(title, description))
        }

        // 设置 LayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 设置 Adapter
        recyclerView.adapter = ItemAdapter(itemList)
    }

    // 生成指定长度的随机字符串
    private fun randomString(random: Random, length: Int): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { chars[random.nextInt(chars.length)] }
            .joinToString("")
    }
}

data class Item(val title: String, val description: String)
