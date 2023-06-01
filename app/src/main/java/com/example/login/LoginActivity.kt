package com.example.login

import android.content.Intent
import com.example.login.databinding.ActivityLoginBinding
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var loggedInUser: UserInfoModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = listOf(
            UserInfoModel("login", "parol"),
        )

        if (Prefs.getUser().isEmpty()) {
            Prefs.setAll(list)
        }

        binding.loginButton.setOnClickListener {

            val username = binding.loginEditText.text.toString().trim().replace("(", "").replace(")", "").replace("\\s".toRegex(), "")
            val password = binding.passwordEditText.text.toString().trim().replace("(", "").replace(")", "").replace("\\s".toRegex(), "")

            if (username.isNotEmpty() && password.isNotEmpty()) {
                loggedInUser = Prefs.getUser()?.find { it.login == username && it.parol == password }
                if (loggedInUser != null) {
                    Prefs.setUser(UserInfoModel(username, password))
                    intent.putExtra("login", username)
                    val registrationIntent = Intent(this, MainActivity::class.java)
                    startActivity(registrationIntent)
                } else {
                    Toast.makeText(this, "Login yoki Parol xato !!!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Hammasini to'ldiring !!!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registrationButton.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }
}
