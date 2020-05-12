package dicoding.com.smkcodingc2.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dicoding.com.smkcodingc2.R
import kotlinx.android.synthetic.main.activity_get_started.*

class GetStarted : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        val id_user = idUser
        if (id_user != "null") {
            gotoCourseActivity()
        }

        tv_login.setOnClickListener { login() }
        tv_btnGetStarted.setOnClickListener { getStarted() }
    }

    private fun getStarted() {
        startActivity(Intent(applicationContext, Slider::class.java))
    }

    private fun login() {
        startActivity(Intent(applicationContext, Login::class.java))
    }

    private fun gotoCourseActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
    }

    private val idUser: String
        private get() {
            val preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
            return preferences.getString("id_user", "null")
        }
}
