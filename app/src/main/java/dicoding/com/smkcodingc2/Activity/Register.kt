package dicoding.com.smkcodingc2.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dicoding.com.smkcodingc2.R
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        tv_login.setOnClickListener { gotoLogin() }
    }

    private fun gotoLogin() {
        val intent = Intent(this, Login :: class.java)
        startActivity(intent)
    }
}
