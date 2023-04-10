package com.example.navfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val toastMaker = ToastMaker
    private var isDouble = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        Log.d("MainActivity", "backButton Clicked")

        if (isDouble) finish()

        toastMaker.makeToast(this, "종료하려면 한번 더 눌러주세요")
        isDouble = true
        Handler().postDelayed( { isDouble = false }, 2000)
    }

}