package dicoding.com.smkcodingc2.Request

import dicoding.com.smkcodingc2.KotlinGenerate.WisataItem
import retrofit2.Call
import retrofit2.http.GET
interface GithubService {
    @GET("preview_wisata.php")
    fun getUsers(): Call<List<WisataItem>>
}