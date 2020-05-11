package dicoding.com.smkcodingc2.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dicoding.com.smkcodingc2.R
import kotlinx.android.synthetic.main.activity_get_started.*

class GetStarted : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        tv_login.setOnClickListener { login() }
        tv_btnGetStarted.setOnClickListener { getStarted() }
    }

    private fun getStarted() {
        startActivity(Intent(applicationContext, Login::class.java))
    }

    private fun login() {
        startActivity(Intent(applicationContext, Login::class.java))
    }
}
