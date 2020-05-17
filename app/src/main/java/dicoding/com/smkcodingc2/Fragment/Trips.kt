package dicoding.com.smkcodingc2.Fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.google.android.material.tabs.TabLayoutMediator
import dicoding.com.smkcodingc2.Adapter.ViewPagerAdapter

import dicoding.com.smkcodingc2.R
import kotlinx.android.synthetic.main.fragment_trips.*
import kotlinx.android.synthetic.main.fragment_trips.tab_layout
import kotlinx.android.synthetic.main.fragment_trips.view_pager

/**
 * A simple [Fragment] subclass.
 */
class Trips : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trips, container, false)
    }

    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val menuTeks = arrayOf("News", "Finished", "Favorites")
        val adapter = ViewPagerAdapter(activity)
        view_pager.setAdapter(adapter);
        TabLayoutMediator(tab_layout, view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = menuTeks[position]
            }).attach()
    }

}
