package dicoding.com.smkcodingc2.Request

import com.google.gson.annotations.SerializedName

class RegisterRequest(@SerializedName("email") var email: String,
                      @SerializedName("password") var password: String,
                      @SerializedName("first_name") var first_name: String,
                      @SerializedName("last_name") var last_name: String)