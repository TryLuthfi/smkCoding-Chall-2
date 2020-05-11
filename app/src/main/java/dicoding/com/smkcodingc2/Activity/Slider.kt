package dicoding.com.smkcodingc2.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import dicoding.com.smkcodingc2.Adapter.IntroSlideAdapter
import dicoding.com.smkcodingc2.R
import dicoding.com.smkcodingc2.Request.IntroSlide
import kotlinx.android.synthetic.main.activity_slider.*

class Slider : AppCompatActivity() {

    private val introSlideAdapter = IntroSlideAdapter(
        listOf(
            IntroSlide(
                "Plan your trips",
                "Book one of our unique hotel to escape the ordinary",
                R.drawable.ic_adventure
            ),
            IntroSlide(
                "Find Best Deals",
                "Fund dealsfor any season from cosy country homes to sity flats",
                R.drawable.ic_offroad
            ),
            IntroSlide(
                "Nikmati Liburanmu",
                "Book one of our unique hotel to escape the ordinary",
                R.drawable.ic_holiday
            )

        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)
        introSlideViewPager.adapter = introSlideAdapter

        setupIndicator()
        setCurrentIndicator(0)
        introSlideViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        buttonNext.setOnClickListener {
            if(introSlideViewPager.currentItem +1 < introSlideAdapter.itemCount){
                introSlideViewPager.currentItem += 1
            } else{
                startActivity(Intent(applicationContext, GetStarted::class.java))
            }
        }
        tv_skipintro.setOnClickListener {
            startActivity(Intent(applicationContext, GetStarted::class.java))
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = indicatorContainer.childCount
        for(i in 0 until childCount){
            val ImageView = indicatorContainer[i] as ImageView
            if(i == index){
                ImageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else{
                ImageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }

    }

    private fun setupIndicator() {
        val indicator = arrayOfNulls<ImageView>(introSlideAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for(i in indicator.indices){
            indicator[i] = ImageView(applicationContext)
            indicator[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorContainer.addView(indicator[i])
        }
    }



}
