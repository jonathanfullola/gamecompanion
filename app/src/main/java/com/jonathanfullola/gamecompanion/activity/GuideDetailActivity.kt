package com.jonathanfullola.gamecompanion.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.jonathanfullola.gamecompanion.R
import kotlinx.android.synthetic.main.activity_guide_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class GuideDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_detail)
        if(intent.hasExtra("guideTitle") && intent.hasExtra("guideImage") && intent.hasExtra("guideDescription")){
            val title = intent.getStringExtra("guideTitle")
            val image = intent.getStringExtra("guideImage")
            val textDescription = intent.getStringExtra("guideDescription")
            guideTitle.setText(title)
            Glide.with(this).load(image).into(guideImageView)
            description.setText(textDescription)
        }

    }



}
