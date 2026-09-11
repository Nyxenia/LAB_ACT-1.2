package com.example.lab_act_12

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvWelcomeMessage: TextView
    private lateinit var tvInstruction: TextView

    private val validationLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val username = result.data?.getStringExtra("USERNAME_KEY")

            etUsername.visibility = View.GONE
            etPassword.visibility = View.GONE
            btnLogin.visibility = View.GONE
            tvInstruction.visibility = View.GONE

            tvWelcomeMessage.text = getString(R.string.welcome_message, username)
            tvWelcomeMessage.visibility = View.VISIBLE
        } else {
            etPassword.text.clear()
            Toast.makeText(this, getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvWelcomeMessage = findViewById(R.id.tvWelcomeMessage)
        tvInstruction = findViewById(R.id.tvInstruction)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, getString(R.string.fill_fields), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, ValidationActivity::class.java).apply {
                putExtra("INPUT_USERNAME", username)
                putExtra("INPUT_PASSWORD", password)
            }
            validationLauncher.launch(intent)
        }
    }
}