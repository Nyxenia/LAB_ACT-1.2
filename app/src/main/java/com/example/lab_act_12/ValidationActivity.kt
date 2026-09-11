package com.example.lab_act_12

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ValidationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inputUser = intent.getStringExtra("INPUT_USERNAME")?.trim()
        val inputPass = intent.getStringExtra("INPUT_PASSWORD")?.trim()

        // Updated logic: Accept ANY username, as long as the password is "admin"
        if (!inputUser.isNullOrBlank() && inputPass == "admin") {
            val resultIntent = Intent().apply {
                putExtra("USERNAME_KEY", inputUser)
            }
            setResult(RESULT_OK, resultIntent)
        } else {
            setResult(RESULT_CANCELED)
        }

        finish()
    }
}