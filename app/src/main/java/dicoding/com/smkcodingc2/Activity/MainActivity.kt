package dicoding.com.smkcodingc2.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dicoding.com.smkcodingc2.Adapter.ViewPagerAdapter
import dicoding.com.smkcodingc2.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuTeks = arrayOf("Explore", "Trips", "Profile")
        val manuIcon = arrayOf(R.drawable.ic_explore, R.drawable.ic_heart,
            R.drawable.ic_users)

        val adapter = ViewPagerAdapter(this)
        view_pager.setAdapter(adapter);
        TabLayoutMediator(tab_layout, view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = menuTeks[position]
                tab.icon = ResourcesCompat.getDrawable(resources,
                manuIcon[position], null)
            }).attach()

    }
}
