package com.example.databindingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.databindingex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. findViewById 방식
        val btn = findViewById<Button>(R.id.testBtnId)
        btn.setOnClickListener {
            Toast.makeText(this, "click", Toast.LENGTH_LONG).show()
        }

        // 2. DataBinding 방식 (gradle: dataBinding { enabled true })
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.testBtnId.setOnClickListener {
            Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show()
        }

        // 로그 남기기 (하단 Logcat에서 확인)  --> Toast 대신 로그 사용 습관 들일 것
        Log.d("MainActivity", "debug 로그")
        Log.e("MainActivity", "error 로그")
        Log.w("MainActivity", "warning 로그")
        Log.i("MainActivity", "info 로그")
        Log.v("MainActivity", "verbose 상세 로그")
    }
}