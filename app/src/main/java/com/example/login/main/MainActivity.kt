package com.example.login.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.login.auth.SignActivity
import com.example.login.model.UserInfoModel
import com.example.login.databinding.ActivityMainBinding
import com.example.login.utils.Prefs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var user: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            startActivity(Intent(this, SignActivity::class.java))
            finish()
        }

        // Foydalanuvchini Prefs-dan oling
        val userList = Prefs.getUser()
        if (userList.isNotEmpty()) {
            user = userList.last().login
        }

        // Agar mavjud bo'lsa, foydalanuvchini Prefs-dan oling
        val intentUser = intent.getStringExtra("login")
        if (intentUser != null) {
            // Foydalanuvchini Prefs-ga saqlang
            Prefs.setUser(UserInfoModel(intentUser, ""))
            user = intentUser
        }

        // Kirish bilan ishlash
        if (!user.isNullOrEmpty()) {
            Prefs.setUser(intentUser?.let { UserInfoModel(it, "") })
            binding.welcomeTextView.text = "Xush kelibsiz, $user!"
        } else {
            binding.welcomeTextView.text = "Login sahifasiga kirib kirishingizni kutamiz."
        }
    }
}
