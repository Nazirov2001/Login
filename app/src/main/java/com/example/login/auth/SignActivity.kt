package com.example.login.auth

import android.content.Intent
import com.example.login.databinding.ActivitySignBinding
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login.model.UserInfoModel
import com.example.login.main.MainActivity
import com.example.login.utils.Prefs

class SignActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignBinding
    private var loggedInUser: UserInfoModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = listOf(
            UserInfoModel("login", "parol"),
        )

        if (Prefs.getUser().isEmpty()) {
            Prefs.setAll(list)
        }

        binding.loginButton.setOnClickListener {

            val username = binding.loginEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()


            if (username.isNotEmpty() && password.isNotEmpty()) {
                loggedInUser =
                    Prefs.getUser()?.find { it.login == username && it.parol == password }
                if (loggedInUser != null) {
                    Prefs.setUser(UserInfoModel(username, password))
                    intent.putExtra("login", username)
                    val registrationIntent = Intent(this, MainActivity::class.java)
                    startActivity(registrationIntent)
                    finish()
                } else {
                    Toast.makeText(this, "Login yoki Parol xato !!!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "Iltimos, barcha maydonlarni to'ldiring !!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.registrationButton.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish()
        }
    }
}
