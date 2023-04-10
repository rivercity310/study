package com.example.dice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.dice.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dice1 = binding.dice1
        val dice2 = binding.dice2

        binding.startBtn.setOnClickListener {
            Toast.makeText(this, "주사위 Go!", Toast.LENGTH_LONG).show()

            val r1 = Random.nextInt(1, 6)
            val r2 = Random.nextInt(1, 6)
            Log.d("MainActivity", r1.toString())
            Log.d("MainActivity", r2.toString())

            setRandomDice(dice1, r1)
            setRandomDice(dice2, r2)
        }
    }

    private fun setRandomDice(dice: ImageView, r: Int) = when(r) {
        1 -> dice.setImageResource(R.drawable.dice_1)
        2 -> dice.setImageResource(R.drawable.dice_2)
        3 -> dice.setImageResource(R.drawable.dice_3)
        4 -> dice.setImageResource(R.drawable.dice_4)
        5 -> dice.setImageResource(R.drawable.dice_5)
        6 -> dice.setImageResource(R.drawable.dice_6)
        else -> throw java.lang.IllegalStateException("State Exception")
    }
}