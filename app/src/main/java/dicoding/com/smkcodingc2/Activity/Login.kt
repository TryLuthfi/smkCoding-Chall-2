package dicoding.com.smkcodingc2.Activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import dicoding.com.smkcodingc2.Konfigurasi.konfigurasi
import dicoding.com.smkcodingc2.R
import dicoding.com.smkcodingc2.Request.encryptMd5
import kotlinx.android.synthetic.main.activity_login.*
import java.math.BigInteger
import java.util.*

class Login : AppCompatActivity() {

    private var hasilmd5: String? = null
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progressDialog = ProgressDialog(this)

        invalid_login.visibility = View.GONE

        val id_user = idUser
        if (id_user != "null") {
            gotoCourseActivity()
        }

        log_facebook.setOnClickListener { TOAST() }
        log_twitter.setOnClickListener { TOAST() }
        tv_btnLogin.setOnClickListener { btnMD5() }
        tv_signup.setOnClickListener { gotoRegister() }

    }

    private fun TOAST() {
        Toast.makeText(applicationContext, "Fitur Belum Dibuat",Toast.LENGTH_SHORT).show()
    }


    private fun gotoRegister() {
        startActivity(Intent (this, Register :: class.java))
    }

    fun btnMD5() {
        val md5input = et_password!!.text.toString().toByteArray()
        var md5Data: BigInteger? = null
        try {
            md5Data = BigInteger(1, encryptMd5.encryptMD5(md5input))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        var md5Str = md5Data!!.toString(16)
        if (md5Str.length < 32) {
            md5Str = "0$md5Str"
        }
        hasilmd5 = md5Str

        if(et_email!!.text.toString().isEmpty()){
            et_email.error = "Harap isi email anda"
        } else if(et_password!!.text.toString().isEmpty()){
            et_password.error = "Harap isi password"
        }else {
            login()
        }
    }

    private fun login() {
        progressDialog!!.setMessage("Login Process...")
        showDialog()

        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, konfigurasi.LOGIN_URL,
            Response.Listener { response ->
                if (response.contains(konfigurasi.LOGIN_SUCCESS)) {
                    hideDialog()
                    val id_user = response.split(";").toTypedArray()[1]
                    Log.e("iniidpelanggan", id_user)
                    setPreference(id_user)
                    gotoCourseActivity()
                } else {
                    hideDialog()
                    invalid_login.text = "Invalid login or password"
                    invalid_login.visibility = View.VISIBLE
                }
            },
            Response.ErrorListener {
                hideDialog()
                invalid_login.text = "The server unreachable"
                invalid_login.visibility = View.VISIBLE
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params[konfigurasi.KEY_EMAIL] = et_email.text.toString()
                params[konfigurasi.KEY_PASSWORD] = hasilmd5!!
                return params
            }
        }
        Volley.newRequestQueue(this).add(stringRequest)
    }

    private fun gotoCourseActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
    }

    private fun showDialog() {
        if (!progressDialog!!.isShowing) progressDialog!!.show()
    }

    private fun hideDialog() {
        if (progressDialog!!.isShowing) progressDialog!!.dismiss()
    }

    fun setPreference(id_user: String?) {
        val mSettings = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val editor = mSettings.edit()
        editor.putString("id_user", id_user)
        editor.apply()
    }

    private val idUser: String
        private get() {
            val preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
            return preferences.getString("id_user", "null")
        }
}
