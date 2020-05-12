package dicoding.com.smkcodingc2.Activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayoutMediator
import dicoding.com.smkcodingc2.Adapter.ViewPagerAdapter
import dicoding.com.smkcodingc2.Fragment.*
import dicoding.com.smkcodingc2.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(R.id.frame_container, Explore())
        fragmentTransaction.commit()

        explore.setOnClickListener { btnExplore() }
        trips.setOnClickListener { btnTrips() }
        profile.setOnClickListener { btnProfile() }
    }

    private fun btnExplore() {
        trips.setTextColor(Color.parseColor("#6E7275"))
        explore.setTextColor(Color.parseColor("#2196F3"))
        profile.setTextColor(Color.parseColor("#6E7275"))
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(R.id.frame_container, Explore())
        fragmentTransaction.commit()
    }

    private fun btnTrips() {
        trips.setTextColor(Color.parseColor("#2196F3"))
        explore.setTextColor(Color.parseColor("#6E7275"))
        profile.setTextColor(Color.parseColor("#6E7275"))
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(R.id.frame_container, Trips())
        fragmentTransaction.commit()
    }

    private fun btnProfile() {
        trips.setTextColor(Color.parseColor("#6E7275"))
        explore.setTextColor(Color.parseColor("#6E7275"))
        profile.setTextColor(Color.parseColor("#2196F3"))
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(R.id.frame_container, Profil())
        fragmentTransaction.commit()
    }
}
