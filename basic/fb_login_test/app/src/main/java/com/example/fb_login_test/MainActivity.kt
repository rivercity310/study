package com.example.fb_login_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.noEmailLoginBtn)
            .setOnClickListener {
                auth = Firebase.auth

                auth.signInAnonymously()
                    .addOnCompleteListener(this) {

                        // login Successful
                        if (it.isSuccessful) {
                            Log.d("MainActivity", "signInAnonymously : success")
                            val user = auth.currentUser
                        }

                        // login Fail
                        else {
                            Log.w("MainActivity", "signInAnonymously : fail", it.exception)
                            Toast.makeText(baseContext, "Auth failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        findViewById<Button>(R.id.emailLoginBtn)
            .setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
    }
}