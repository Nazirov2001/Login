package com.example.login

import java.io.Serializable

data class UserInfoModel(
    val login: String,
    val parol: String,
): Serializable