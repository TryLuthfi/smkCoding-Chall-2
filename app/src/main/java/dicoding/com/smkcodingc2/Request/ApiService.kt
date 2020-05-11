package dicoding.com.smkcodingc2.Request

import dicoding.com.smkcodingc2.Konfigurasi.konfigurasi
import retrofit2.Retrofit

object ApiService {
    private val TAG = "--ApiService"


    fun loginApiCall() = Retrofit.Builder()
        .baseUrl(konfigurasi.REGISTRASI_URL)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ApiWorker.gsonConverter)
        .client(ApiWorker.client)
        .build()
        .create(ApiList::class.java)!!
}