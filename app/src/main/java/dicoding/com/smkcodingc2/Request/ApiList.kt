package dicoding.com.smkcodingc2.Request

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiList {

    @POST("register.php")
    fun doRegister(
        @Body registerRequest: RegisterRequest
    ): Call<RegisterResponse> // body data

}