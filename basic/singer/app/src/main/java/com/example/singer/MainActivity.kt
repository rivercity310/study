package com.example.singer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listItem = mutableListOf<ListViewModel>(
            ListViewModel("a", "1"),
            ListViewModel("b", "2"),
            ListViewModel("c", "3"),
            ListViewModel("d", "4"),
            ListViewModel("e", "5")
        )
        val adapter = ListViewAdapter(listItem)
        val listView = findViewById<ListView>(R.id.mainListView)

        listView.adapter = adapter
        listView.setOnItemClickListener { adapterView, view, i, id ->
            val txt = "${listItem[i].text1}, ${listItem[i].text2}"
            Toast.makeText(this, txt, Toast.LENGTH_LONG).show()
            Log.d("MainActivity", "$adapterView, $view, $i, $id")
        }

        val btn = findViewById<Button>(R.id.mainBtn)
        btn.setOnClickListener {
            val intent = Intent(this, RecyclerViewActivity::class.java)
            intent.putExtra("test", "하잉")
            startActivity(intent)
        }
    }
}