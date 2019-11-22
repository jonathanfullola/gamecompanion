package com.jonathanfullola.gamecompanion.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics
import com.jonathanfullola.gamecompanion.R
import com.jonathanfullola.gamecompanion.fragment.ChatFragment
import com.jonathanfullola.gamecompanion.fragment.GuideFragment
import com.jonathanfullola.gamecompanion.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this){}
        val adRequest = AdRequest.Builder().build()
        bannerAdView.loadAd(adRequest)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            //switch menu id
            when(menuItem.itemId){
                R.id.guide -> {
                    FirebaseAnalytics.getInstance(this).logEvent("Guide_Tab_Click",null)
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

                    // Obtain the FirebaseAnalytics instance.
                    FirebaseAnalytics.getInstance(this).logEvent("Chat_Tab_Click",null)
                    //create fragment
                    var chatFragment = ChatFragment()
                    //add fragment to fragment container
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.add(fragmentContainer.id, chatFragment)
                    fragmentTransaction.commit()


                }

                R.id.profile -> {
                    FirebaseAnalytics.getInstance(this).logEvent("Profile_Tab_Click",null)
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
