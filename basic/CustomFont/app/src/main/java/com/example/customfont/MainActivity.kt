package com.example.customfont

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var isDouble = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listItem = mutableListOf<ListViewModel>()
        listItem.add(ListViewModel("A", "B"))
        listItem.add(ListViewModel("C", "D"))
        listItem.add(ListViewModel("E", "F"))

        val adapter = ListViewAdapter(listItem)

        val listView = findViewById<ListView>(R.id.mainListView)
        listView.adapter = adapter
    }

    // 뒤로가기 버튼 감지
    override fun onBackPressed() {
        Log.d("MainActivity", "backButton")
        if (isDouble) finish()

        isDouble = true
        Toast.makeText(this, "종료하시려면 더블클릭", Toast.LENGTH_LONG).show()

        Handler().postDelayed( { isDouble = false }, 2000)
    }
}