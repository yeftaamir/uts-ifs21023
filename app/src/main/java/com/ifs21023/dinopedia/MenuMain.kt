package com.ifs21023.dinopedia

import android.content.Context
import android.content.Intent

class MenuMain {
    companion object {
        fun navigateToAboutActivity(context: Context) {
            val intent = Intent(context, AboutActivity::class.java)
            context.startActivity(intent)
        }
    }
}