package dicoding.com.smkcodingc2.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import dicoding.com.smkcodingc2.Adapter.ExploreAdapter
import dicoding.com.smkcodingc2.KotlinGenerate.WisataItem
import dicoding.com.smkcodingc2.R
import dicoding.com.smkcodingc2.Request.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_my_friends.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Explore : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_friends, container, false)
    }

    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetGithubUser()
    }

    private fun callApiGetGithubUser() {
        showLoading(context!!, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest = apiRequest<GithubService>(httpClient)
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
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }

    private fun tampilGithubUser(githubUsers: List<WisataItem>) {
        kategorirRecyclerView.layoutManager = LinearLayoutManager(context)
        kategorirRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)
        kategorirRecyclerView!!.setHasFixedSize(true)
        kategorirRecyclerView!!.isNestedScrollingEnabled = true
        kategorirRecyclerView.adapter = ExploreAdapter(context!!, githubUsers) {
            val githubUser = it
            tampilToast(context!!, githubUser.deskripsi)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }


}
