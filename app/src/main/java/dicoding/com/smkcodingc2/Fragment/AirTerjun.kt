package dicoding.com.smkcodingc2.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dicoding.com.smkcodingc2.Adapter.ExploreAdapter
import dicoding.com.smkcodingc2.ApiRequest.AirTerjunService
import dicoding.com.smkcodingc2.ApiRequest.GunungService
import dicoding.com.smkcodingc2.ApiRequest.apiRequest
import dicoding.com.smkcodingc2.ApiRequest.httpClient
import dicoding.com.smkcodingc2.KotlinGenerate.WisataItem

import dicoding.com.smkcodingc2.R
import dicoding.com.smkcodingc2.Request.dismissLoading
import dicoding.com.smkcodingc2.Request.showLoading
import dicoding.com.smkcodingc2.Request.tampilToast
import kotlinx.android.synthetic.main.fragment_gunung.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class AirTerjun : Fragment() {

    var popular = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_air_terjun, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callApiGetWisata()
    }

    private fun callApiGetWisata() {
        showLoading(context!!, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest =
            apiRequest<AirTerjunService>(
                httpClient
            )
        val call = apiRequest.getUsers()
        call.enqueue(object : Callback<List<WisataItem>> {
            override fun onFailure(call: Call<List<WisataItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }
            override fun onResponse(call: Call<List<WisataItem>>, response:
            Response<List<WisataItem>>
            ) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilWisata(response.body()!!)
                            else -> {
                                wisataRecyclerView.visibility = View.VISIBLE
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else -> {
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }

    private fun tampilWisata(githubUsers: List<WisataItem>) {
        wisataRecyclerView.layoutManager = LinearLayoutManager(context)
        wisataRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)
        wisataRecyclerView.setHasFixedSize(false)
        wisataRecyclerView.isNestedScrollingEnabled = false
        wisataRecyclerView.adapter = ExploreAdapter(context!!, githubUsers) {
            val githubUser = it
            popular = githubUser.nama
            tampilToast(context!!, popular)
        }
        wisataRecyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
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

}
