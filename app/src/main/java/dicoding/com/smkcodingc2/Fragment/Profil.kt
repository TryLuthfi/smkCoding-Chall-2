package dicoding.com.smkcodingc2.Fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dicoding.com.smkcodingc2.Activity.EditProfile
import dicoding.com.smkcodingc2.Activity.Login
import dicoding.com.smkcodingc2.Konfigurasi.konfigurasi
import dicoding.com.smkcodingc2.R
import kotlinx.android.synthetic.main.fragment_profil.*

class Profil : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        btn_change.setOnClickListener { changeProfile() }

        val requestOptions = RequestOptions()
            .placeholder(R.color.colorBlack)

        Glide.with(context!!)
            .load("https://htmlcolors.com/color-image/000000.png").apply(requestOptions)
            .into(imageProfile)

        val idUserV = idUser
        val emailV = email
        val passwordV = password
        val firstNameV = first_name
        val lastNameV = last_name
        val imageProfileV = image_profile
        if(idUserV != "empty" && emailV != "empty" && passwordV != "empty" && firstNameV != "empty" && lastNameV != "empty"){
            tv_username.setText(firstNameV+lastNameV)
            tv_email.setText(emailV)

            Glide.with(context!!.applicationContext)
                .load(konfigurasi.KATEGORI_URL+imageProfileV).apply(requestOptions)
                .into(imageProfile)
        }

        Glide.with(context!!)
            .load("https://htmlcolors.com/color-image/ffffff.png").apply(requestOptions)
            .into(white_circle)

        btn_logout.setOnClickListener {
            val preferences: SharedPreferences = activity!!.getSharedPreferences("Settings", Context.MODE_PRIVATE)
            preferences.edit().clear().commit()
            val intent = Intent(activity!!.getApplicationContext(), Login::class.java)
            startActivity(intent)
            activity!!.finish()
        }


    }

    private fun changeProfile() {
        startActivity(Intent(context!!.applicationContext, EditProfile::class.java))
    }

    private val idUser: String
        private get() {
            val preferences = context!!.getSharedPreferences("Settings", Context.MODE_PRIVATE)
            return preferences.getString("id_user", "null")
        }

    private val email: String
        private get() {
            val preferences = context!!.getSharedPreferences("Settings", Context.MODE_PRIVATE)
            return preferences.getString("email", "null")
        }

    private val password: String
        private get() {
            val preferences = context!!.getSharedPreferences("Settings", Context.MODE_PRIVATE)
            return preferences.getString("password", "null")
        }

    private val first_name: String
        private get() {
            val preferences = context!!.getSharedPreferences("Settings", Context.MODE_PRIVATE)
            return preferences.getString("first_name", "null")
        }

    private val last_name: String
        private get() {
            val preferences = context!!.getSharedPreferences("Settings", Context.MODE_PRIVATE)
            return preferences.getString("last_name", "null")
        }

    private val image_profile: String
        private get() {
            val preferences = context!!.getSharedPreferences("Settings", Context.MODE_PRIVATE)
            return preferences.getString("image_profile", "null")
        }
}
