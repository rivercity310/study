package com.example.singer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)

        val test = intent.getStringExtra("test")
        Log.d("RecycleViewActivity", test.toString())

        val items = mutableListOf<RecyclerViewModel>(
            RecyclerViewModel("황승수", 27),
            RecyclerViewModel("김수연", 24),
            RecyclerViewModel("aaa", 30)
        )

        val rv = findViewById<RecyclerView>(R.id.rv)
        val rvAdapter = RVAdapter(items)

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)

        rvAdapter.itemClick = object: RVAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                Toast.makeText(baseContext, items[position].toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}