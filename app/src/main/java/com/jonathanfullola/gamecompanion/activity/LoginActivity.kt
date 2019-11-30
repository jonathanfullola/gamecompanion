package com.jonathanfullola.gamecompanion.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.jonathanfullola.gamecompanion.R

import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progresbar.visibility = View.GONE

        loginbutton.setOnClickListener{
            val email = loginemailText.text?.toString().orEmpty()
            val password = loginpasswordText.text?.toString().orEmpty()

            progresbar.visibility = View.VISIBLE

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnSuccessListener {
                    FirebaseAnalytics.getInstance(this).logEvent("Login_Succesfull",null)
                    finish()
                }
                .addOnFailureListener{
                    progresbar.visibility = View.GONE
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                }
        }
    }

    override fun onResume() {
        super.onResume()

        progresbar.visibility = View.GONE
    }
}