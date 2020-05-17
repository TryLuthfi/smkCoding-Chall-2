package dicoding.com.smkcodingc2.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import dicoding.com.smkcodingc2.Adapter.KategoriAdapter
import dicoding.com.smkcodingc2.ApiRequest.GunungService
import dicoding.com.smkcodingc2.ApiRequest.KategoriService
import dicoding.com.smkcodingc2.ApiRequest.apiRequest
import dicoding.com.smkcodingc2.ApiRequest.httpClient
import dicoding.com.smkcodingc2.KotlinGenerate.KategoriItem
import dicoding.com.smkcodingc2.KotlinGenerate.WisataItem
import dicoding.com.smkcodingc2.R
import dicoding.com.smkcodingc2.Request.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_explore.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Explore : Fragment() {

    var popular: String = "Gunung"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        no_internet.visibility = View.GONE
        callApiGetKategori()
        setFragment()
    }

    private fun setFragment() {
        val fm = childFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(R.id.frameLayout, Gunung())
        fragmentTransaction.commit()
    }

    private fun callApiGetKategori() {
        showLoading(context!!, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest =
            apiRequest<KategoriService>(
                httpClient
            )
        val call = apiRequest.getUsers()
        call.enqueue(object : Callback<List<KategoriItem>> {
            override fun onFailure(call: Call<List<KategoriItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
                no_internet.visibility = View.VISIBLE
            }
            override fun onResponse(call: Call<List<KategoriItem>>, response:
            Response<List<KategoriItem>>
            ) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilKategori(response.body()!!)
                            else -> {
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else -> {
                        no_internet.visibility = View.VISIBLE
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }



    private fun tampilKategori(githubUsers: List<KategoriItem>) {
        kategoriRecyclerView.layoutManager = LinearLayoutManager(context)
        kategoriRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
        kategoriRecyclerView.setHasFixedSize(true)
        kategoriRecyclerView.isNestedScrollingEnabled = false
        kategoriRecyclerView.adapter = KategoriAdapter(-1, context!!, githubUsers) {
            val githubUser = it
            popular = githubUser.nama
            tampilToast(context!!, popular)
            replaceFragment()
        }
        kategoriRecyclerView.addOnItemTouchListener(object : OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val action = e.action
                when (action) {
                    MotionEvent.ACTION_DOWN -> rv.parent
                        .requestDisallowInterceptTouchEvent(true)
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
    }

    private fun replaceFragment() {
        if(popular.contains("Gunung")){
            val fm = childFragmentManager
            val fragmentTransaction = fm.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, Gunung())
            fragmentTransaction.commit()
        } else if(popular.contains("Air Terjun")){
            val fm = childFragmentManager
            val fragmentTransaction = fm.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, AirTerjun())
            fragmentTransaction.commit()
        } else if(popular.contains("Danau")){
            val fm = childFragmentManager
            val fragmentTransaction = fm.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, Danau())
            fragmentTransaction.commit()
        } else {
            Toast.makeText(context, "Kategori not found", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }


}
