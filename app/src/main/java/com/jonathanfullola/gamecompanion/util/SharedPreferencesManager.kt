package com.jonathanfullola.gamecompanion.util

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jonathanfullola.gamecompanion.model.UserModel
import kotlinx.android.synthetic.main.fragment_profile.*

class SharedPreferencesManager{

    val userPreferencesFileName = "userPreferences";
    val usernameKey = "username";
    val imagekey = "image"

    fun getUsername(context: Context):String? {

        val sharedPreferences = context.getSharedPreferences(userPreferencesFileName, Context.MODE_PRIVATE)
        return sharedPreferences.getString(usernameKey, null)
    }
    fun setUsername(context: Context, username:String?){
        val sharedPreferences = context.getSharedPreferences(userPreferencesFileName, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(usernameKey,username).apply()
    }

    fun clear(context: Context){
        val sharedPreferences = context.getSharedPreferences(userPreferencesFileName, Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }

    fun setAvatar(context: Context,image:String?){
        val sharedPreferences = context.getSharedPreferences(userPreferencesFileName, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(imagekey,image).apply()
    }

    fun getAvatar(context: Context):String?{
        val sharedPreferences = context.getSharedPreferences(userPreferencesFileName, Context.MODE_PRIVATE)
        return sharedPreferences.getString(imagekey,null)
    }

}