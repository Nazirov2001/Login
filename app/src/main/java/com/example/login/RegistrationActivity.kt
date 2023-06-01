package com.example.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.login.databinding.ActivityRegistrationBinding
import android.content.Intent
import android.widget.Toast

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Prefs)

        binding.registerButton.setOnClickListener {

            val username = binding.loginEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            val confirmPassword = binding.confirmPasswordEditText.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {

                    Prefs.setUser(UserInfoModel(username, password))
                    intent.putExtra("login", username)

                    Toast.makeText(this, "Ro'yhatdan o'tish muvaffaqiyatli amalga oshirildi", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Parol va parolni tasdiqlash mos kelmaydi", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Iltimos, barcha maydonlarni to'ldiring", Toast.LENGTH_SHORT).show()
            }
        }

    }
}

