package com.example.goodwords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class SentenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sentence)

        val adapter = ListViewAdapter(MainActivity.getSentenceList())
        val listview = findViewById<ListView>(R.id.sentenceListView)

        listview.adapter = adapter
    }
}