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
        if(intent.hasExtra("guideTitle") && intent.hasExtra("guideImage")){
            val title = intent.getStringExtra("guideTitle")
            val image = intent.getStringExtra("guideImage")
            guideTitle.setText(title)
            //Glide.with(holder.image.context).load(image).into(holder.image)
            description.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris condimentum tincidunt enim eu vulputate. Cras convallis tristique felis eget porttitor. Donec tortor risus, efficitur at pulvinar ut, aliquam sit amet nibh. Duis auctor auctor urna, in fringilla ligula lacinia non. Phasellus vel faucibus orci. Praesent diam sem, ultricies eu nibh in, gravida blandit massa. Nullam consectetur maximus enim, eu auctor mauris. Donec id tempus nunc. Suspendisse molestie tellus est, id pulvinar risus posuere vitae. Phasellus et lectus at lorem egestas tincidunt. Vestibulum hendrerit lorem lorem, at placerat elit fringilla congue. Vestibulum dictum rutrum tristique. In arcu metus, vulputate et dui ut, maximus porttitor justo. Nunc nec condimentum dui.")

        }

    }



}
