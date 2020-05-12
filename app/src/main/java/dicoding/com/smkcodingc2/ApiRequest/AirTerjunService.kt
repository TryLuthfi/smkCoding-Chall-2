package dicoding.com.smkcodingc2.ApiRequest

import dicoding.com.smkcodingc2.KotlinGenerate.WisataItem
import retrofit2.Call
import retrofit2.http.GET

interface AirTerjunService {
    @GET("preview_wisata_airterjun.php")
    fun getUsers(): Call<List<WisataItem>>
}