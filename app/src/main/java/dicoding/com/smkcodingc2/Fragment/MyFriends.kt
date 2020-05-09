package dicoding.com.smkcodingc2.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import dicoding.com.smkcodingc2.Adapter.MyFriendAdapter
import dicoding.com.smkcodingc2.Model.mMyFriends

import dicoding.com.smkcodingc2.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_my_friends.*

class MyFriends : Fragment() {

    lateinit var listTeman : ArrayList<mMyFriends>

    private fun simulasiDataTeman() {
        listTeman = ArrayList()
        listTeman.add(mMyFriends("Fakhry", "Laki-laki", "fakhry@smkcoding.id",
            "081123123123", "Malang"))
        listTeman.add(mMyFriends("Ahmad", "Laki-laki", "ahmad@smkcoding.id",
            "085123123123", "Malang"))
    }

    private fun tampilTeman() {
        listMyFriends.layoutManager = LinearLayoutManager(activity)
        listMyFriends.adapter = MyFriendAdapter(activity!!, listTeman)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_friends, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView() {
        simulasiDataTeman()
        tampilTeman()
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
