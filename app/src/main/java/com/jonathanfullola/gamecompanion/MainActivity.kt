package com.jonathanfullola.gamecompanion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottomNavigationView.setOnNavigationItemReselectedListener { menuItem ->
            //switch menu id
            when(menuItem.itemId){
                R.id.guide -> {
                    //create fragment
                    var guideFragment = GuideFragment()
                    //add fragment to fragment container
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.add(fragmentContainer.id, guideFragment)
                    fragmentTransaction.commit()

                    //TODO entregable

                }

                R.id.chat -> {
                    //create fragment
                    var chatFragment = ChatFragment()
                    //add fragment to fragment container
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.add(fragmentContainer.id, chatFragment)
                    fragmentTransaction.commit()


                }

                R.id.profile -> {
                    //create fragment
                    var profileFragment = ProfileFragment()
                    //add fragment to fragment container
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.add(fragmentContainer.id, profileFragment)
                    fragmentTransaction.commit()
                }

            }

            true
        }






    }
}
