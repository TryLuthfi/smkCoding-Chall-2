package dicoding.com.smkcodingc2.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import dicoding.com.smkcodingc2.Adapter.ExploreAdapter
import dicoding.com.smkcodingc2.ApiRequest.GunungService
import dicoding.com.smkcodingc2.ApiRequest.apiRequest
import dicoding.com.smkcodingc2.ApiRequest.httpClient
import dicoding.com.smkcodingc2.KotlinGenerate.WisataItem
import dicoding.com.smkcodingc2.R
import dicoding.com.smkcodingc2.Request.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_explore.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Explore : Fragment() {

    var popular: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        no_internet.visibility = View.GONE
        callApiGetGithubUser()
    }

    private fun callApiGetGithubUser() {
        showLoading(context!!, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest =
            apiRequest<GunungService>(
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
                                tampilGithubUser(response.body()!!)
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

    private fun tampilGithubUser(githubUsers: List<WisataItem>) {
        kategorirRecyclerView.layoutManager = LinearLayoutManager(context)
        kategorirRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)
        kategorirRecyclerView.setHasFixedSize(false)
        kategorirRecyclerView.isNestedScrollingEnabled = false
        kategorirRecyclerView.adapter = ExploreAdapter(context!!, githubUsers) {
            val githubUser = it
            popular = githubUser.nama
            tampilToast(context!!, popular)
        }
        kategorirRecyclerView.addOnItemTouchListener(object : OnItemTouchListener {
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

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }


}
