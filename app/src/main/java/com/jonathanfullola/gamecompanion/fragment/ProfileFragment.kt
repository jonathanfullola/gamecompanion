package com.jonathanfullola.gamecompanion.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jonathanfullola.gamecompanion.R
import com.jonathanfullola.gamecompanion.activity.LoginActivity
import com.jonathanfullola.gamecompanion.activity.RegisterActivity
import com.jonathanfullola.gamecompanion.model.UserModel
import com.jonathanfullola.gamecompanion.util.COLLECTION_USERS
import com.jonathanfullola.gamecompanion.util.SharedPreferencesManager
import kotlinx.android.synthetic.main.fragment_profile.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ProfileFragment : Fragment() {

    //Create Fragment Layout View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        initUI()
    }
    private fun initUI(){
        //check user available
        if(FirebaseAuth.getInstance().currentUser == null){
            loginButton.visibility = View.VISIBLE
            logoutButton.visibility = View.GONE
            usernameTextView.visibility = View.GONE
            loginButton.setOnClickListener{
                //Todo: Go to login screen
                startActivity(Intent(requireContext(),LoginActivity::class.java))
            }

            //show register button
            registerButton.visibility = View.VISIBLE
            registerButton.setOnClickListener {
                //TODO: Go to register
                startActivity(Intent(requireContext(), RegisterActivity::class.java))
            }
        }else{
            //User available
            //Hide register button
            registerButton.visibility = View.GONE
            loginButton.visibility = View.GONE
            usernameTextView.visibility = View.VISIBLE

            //TODO show profile
            logoutButton.visibility = View.VISIBLE
            logoutButton.setOnClickListener{
                //Log out user
                FirebaseAuth.getInstance().signOut()

                //clear shared preferences
                SharedPreferencesManager().clear(requireContext())
                initUI()
            }

            showUser()
        }
    }

    private fun showUser(){
        val username = SharedPreferencesManager().getUsername(requireContext())
        SharedPreferencesManager().getUsername(requireContext())
            ?.let { username ->
                usernameTextView.text = "Hello ${username.capitalize()}" }
            ?: run {
                //Get user profile from firestore
                val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
                FirebaseFirestore.getInstance()
                    .collection(COLLECTION_USERS)
                    .document(userId)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->
                        //Got user profile
                        val userProfile  = documentSnapshot.toObject(UserModel::class.java)

                        //Saver username locally
                        SharedPreferencesManager().setUsername(requireContext(),userProfile?.username)
                    }
                    .addOnFailureListener{
                        Toast.makeText(requireContext(),it.localizedMessage, Toast.LENGTH_LONG).show()
                    }
            }


    }



}
