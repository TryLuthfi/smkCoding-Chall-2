package dicoding.com.smkcodingc2.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.bumptech.glide.Glide
import dicoding.com.smkcodingc2.R
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.imageProfile
import kotlinx.android.synthetic.main.fragment_profil.*

class EditProfile : AppCompatActivity() {
    var email = ""
    var first_name = ""
    var last_name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        hideStatusBar()
        getDataIntent()
    }

    private fun getDataIntent() {
        email = intent.getStringExtra("email")
        first_name = intent.getStringExtra("first_name")
        last_name = intent.getStringExtra("last_name")
        setData()
    }

    private fun setData() {

        Glide.with(applicationContext)
            .load("https://htmlcolors.com/color-image/000000.png")
            .into(imageProfile)

        et_email.setText(email)
        et_first_name.setText(first_name)
        et_last_name.setText(last_name)

        validasi()
    }

    private fun validasi() {
        if(et_ultah.text.toString().isEmpty()){
            et_ultah.setText("-")
        }
        if(et_address.text.toString().isEmpty()){
            et_address.setText("-")
        }
    }

    private fun hideStatusBar() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}
