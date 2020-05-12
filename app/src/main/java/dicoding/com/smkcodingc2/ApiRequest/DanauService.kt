package dicoding.com.smkcodingc2.ApiRequest

import dicoding.com.smkcodingc2.KotlinGenerate.WisataItem
import retrofit2.Call
import retrofit2.http.GET

interface DanauService {
    @GET("preview_wisata_danau.php")
    fun getUsers(): Call<List<WisataItem>>
}