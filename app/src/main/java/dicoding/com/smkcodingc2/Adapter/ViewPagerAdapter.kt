package dicoding.com.smkcodingc2.Adapter

import dicoding.com.smkcodingc2.Fragment.Explore

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import dicoding.com.smkcodingc2.Fragment.Github
import dicoding.com.smkcodingc2.Fragment.Profil

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val JUMLAH_MENU = 3

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> { return Explore() }
            1 -> { return Github() }
            2 -> { return Profil() }
            else -> {
                return Github()
            }
        }
    }
    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }
}