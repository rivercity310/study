package com.example.goodwords

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.goodwords.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        private val sentenceList = mutableListOf<String>()
        internal fun getSentenceList() = sentenceList
    }

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sentenceList.add("1. 검정화면에 대충 흰글씨 쓰면 명언같다.")
        sentenceList.add("2. 검정화면에 대충 흰글씨 쓰면 명언같다.")
        sentenceList.add("3. 검정화면에 대충 흰글씨 쓰면 명언같다.")
        sentenceList.add("4. 검정화면에 대충 흰글씨 쓰면 명언같다.")
        sentenceList.add("5. 검정화면에 대충 흰글씨 쓰면 명언같다.")
        sentenceList.add("6. 검정화면에 대충 흰글씨 쓰면 명언같다.")
        sentenceList.add("7. 검정화면에 대충 흰글씨 쓰면 명언같다.")

        val randomSentence = sentenceList.random()
        Log.d("MainActivity", randomSentence)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.showAllSentenceBtn.setOnClickListener {
            val intent = Intent(this, SentenceActivity::class.java)
            startActivity(intent)
        }

        binding.goodWordTextArea.text = randomSentence
    }

}