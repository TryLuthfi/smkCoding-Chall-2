package dicoding.com.smkcodingc2.ApiRequest

import dicoding.com.smkcodingc2.KotlinGenerate.KategoriItem
import dicoding.com.smkcodingc2.KotlinGenerate.WisataItem
import retrofit2.Call
import retrofit2.http.GET

interface KategoriService {
    @GET("preview_kategori.php")
    fun getUsers(): Call<List<KategoriItem>>
}