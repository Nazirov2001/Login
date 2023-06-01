package com.example.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var user: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Retrieve the user from Prefs
        val userList = Prefs.getUser()
        if (userList.isNotEmpty()) {
            user = userList.last().login
        }



        // Get user from intent if available
        val intentUser = intent.getStringExtra("login")
        if (intentUser != null) {
            // Save user to Prefs
            Prefs.setUser(UserInfoModel(intentUser, ""))
            user = intentUser
        }

        // Login handling
        if (!user.isNullOrEmpty()) {
            Prefs.setUser(intentUser?.let { UserInfoModel(it, "") })
            binding.welcomeTextView.text = "Xush kelibsiz, $user!"
        } else {
            binding.welcomeTextView.text = "Login sahifasiga kirib kirishingizni kutamiz."
        }
    }


}
