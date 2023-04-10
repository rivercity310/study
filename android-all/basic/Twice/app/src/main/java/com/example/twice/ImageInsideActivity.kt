package com.example.twice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

class ImageInsideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_inside)

        val data = intent.getStringExtra("data")
        val memberImage = findViewById<ImageView>(R.id.memberImageArea)
        Toast.makeText(this, data, Toast.LENGTH_LONG).show()

        when (data) {
            "1" -> memberImage.setImageResource(R.drawable.member_1)
            "2" -> memberImage.setImageResource(R.drawable.member_2)
            "3" -> memberImage.setImageResource(R.drawable.member_3)
            "4" -> memberImage.setImageResource(R.drawable.member_4)
            "5" -> memberImage.setImageResource(R.drawable.member_5)
            "6" -> memberImage.setImageResource(R.drawable.member_6)
            "7" -> memberImage.setImageResource(R.drawable.member_7)
            "8" -> memberImage.setImageResource(R.drawable.member_8)
            "9" -> memberImage.setImageResource(R.drawable.member_9)
        }
    }
}