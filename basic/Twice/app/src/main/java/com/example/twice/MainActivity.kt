package com.example.twice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<ImageView>(R.id.member1)
        val btn2 = findViewById<ImageView>(R.id.member2)
        val btn3 = findViewById<ImageView>(R.id.member3)
        val btn4 = findViewById<ImageView>(R.id.member4)
        val btn5 = findViewById<ImageView>(R.id.member5)
        val btn6 = findViewById<ImageView>(R.id.member6)
        val btn7 = findViewById<ImageView>(R.id.member7)
        val btn8 = findViewById<ImageView>(R.id.member8)
        val btn9 = findViewById<ImageView>(R.id.member9)

        val btns = listOf(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)

        btns.forEachIndexed { idx, btn ->
            btn.setOnClickListener {
                val intent = Intent(this, ImageInsideActivity::class.java)
                intent.putExtra("data", (idx + 1).toString())
            }
        }
    }
}