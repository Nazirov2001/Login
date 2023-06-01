package com.example.login.utils

import com.example.login.App
import com.example.login.model.UserInfoModel
import com.orhanobut.hawk.Hawk

const val PREF_USER_SETTINGS = "pref_user_settings"

class Prefs {
    companion object {
        fun init(app: App) {
            Hawk.init(app).build()
        }

        fun setUser(value: UserInfoModel?) {
            val userList = getUser().toMutableList()
            value?.let {
                userList.add(it)
                Hawk.put(PREF_USER_SETTINGS, userList)
            }
        }

        fun setAll(list: List<UserInfoModel>) {
            Hawk.put(PREF_USER_SETTINGS, list)
        }

        fun getUser(): List<UserInfoModel> {
            val user = Hawk.get<Any>(PREF_USER_SETTINGS)
            return if (user is List<*>) {
                @Suppress("UNCHECKED_CAST")
                user as List<UserInfoModel>
            } else {
                emptyList()
            }
        }
    }
}


