package com.jonathanfullola.gamecompanion.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jonathanfullola.gamecompanion.R
import com.jonathanfullola.gamecompanion.model.UserModel
import com.jonathanfullola.gamecompanion.util.COLLECTION_USERS
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        //Listener
        registerbutton.setOnClickListener {
            val username = usernameText.text?.toString().orEmpty()
            val email = emailText.text?.toString().orEmpty()
            val password = passwordText.text?.toString().orEmpty()


     //User validation
            if(username.trim().isEmpty()){
                //Error
                Toast.makeText(this, getString(R.string.invalid_username), Toast.LENGTH_LONG).show()
                //or
                usernameText.error = getString(R.string.invalid_username)
                return@setOnClickListener
            }
     //Email validation
            if(email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                //Error
                Toast.makeText(this, getString(R.string.invalid_email), Toast.LENGTH_LONG).show()
                //or
                emailText.error = getString(R.string.invalid_email)
                return@setOnClickListener
            }
     //password validation

            if(password.isBlank() || !isPasswordValid(password)){
                Toast.makeText(this, getString(R.string.invalid_password), Toast.LENGTH_LONG).show()
                passwordText.error = getString(R.string.invalid_password)
                return@setOnClickListener
            }

    //Create user
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener {  authResult ->
                    Toast.makeText(this,getString(R.string.user_created_succesfully), Toast.LENGTH_LONG).show()
                    finish()
                    val userModel = UserModel(
                        id = authResult.user?.uid ?: "",
                        username = username,
                        email = email
                    )
                    FirebaseFirestore.getInstance().collection(COLLECTION_USERS).document(authResult.user?.uid ?: "").set(userModel)
                        .addOnSuccessListener {  finish()
                        }
                        .addOnFailureListener{

                        }
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                    //Handle errors
                }



        }
    }

    private fun isPasswordValid(password: String):Boolean{
        //contain letter and number
        //password >= 4 characters
        var digit = false
        var letter = false

        if(password.length < 4)
            return false

        for (i in password.indices ){
            if(password[i].isDigit() ){ digit = true }
            if(password[i].isLetter() ){ letter = true }
        }
        return digit && letter;
    }

}
