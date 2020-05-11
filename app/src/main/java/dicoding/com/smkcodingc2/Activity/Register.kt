package dicoding.com.smkcodingc2.Activity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import dicoding.com.smkcodingc2.R
import dicoding.com.smkcodingc2.Request.ApiService
import dicoding.com.smkcodingc2.Request.RegisterRequest
import dicoding.com.smkcodingc2.Request.RegisterResponse
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.tv_login
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register : AppCompatActivity() {

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        progressDialog = ProgressDialog(this)

        btn_signup.setOnClickListener { ValidasiRegister() }
        tv_login.setOnClickListener { gotoLogin() }
    }

    private fun ValidasiRegister() {

        if (validation()) {
            val json = JSONObject()
            json.put("email", et_email.text.toString())
            json.put("password", et_password.text.toString())
            json.put("first_name", et_firstname.text.toString())
            json.put("last_name", et_lastname.text.toString())

            progressDialog!!.setMessage("Login Process...")
            showDialog()

            ApiService.loginApiCall().doRegister(
                RegisterRequest(
                    et_email.text.toString(),
                    et_password.text.toString(),
                    et_firstname.text.toString(),
                    et_lastname.text.toString()
                )
            ).enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {

                    Log.d("Response::::", response.body().toString())
                    val loginResponse :  RegisterResponse
                    loginResponse = response.body()!!
                    if (loginResponse.status.equals("true") || loginResponse.message.equals("Successfully registered!")){
                        hideDialog()
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                    }else{
                        hideDialog()
                        Toast.makeText(applicationContext, response.body()!!.message, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    hideDialog()
                }

            })
        }
    }

    private fun gotoLogin() {
        onBackPressed()
    }

    fun validation(): Boolean {
        var value = true

        val email = et_email.text.toString().trim()
        val password = et_password.text.toString().trim()
        val first_name = et_firstname.text.toString().trim()
        val last_name = et_lastname.text.toString().trim()

        if (first_name.isEmpty()) {
            et_firstname.error = "Firstname required"
            et_firstname.requestFocus()
            value = false
        }

        if (last_name.isEmpty()) {
            et_lastname.error = "Firstname required"
            et_lastname.requestFocus()
            value = false
        }
        if (email.isEmpty()) {
            et_email.error = "Email required"
            et_email.requestFocus()
            value = false
        }


        if (password.isEmpty()) {
            et_password.error = "Password required"
            et_password.requestFocus()
            value = false
        }

        return value;
    }

    private fun showDialog() {
        if (!progressDialog!!.isShowing) progressDialog!!.show()
    }

    private fun hideDialog() {
        if (progressDialog!!.isShowing) progressDialog!!.dismiss()
    }
}

