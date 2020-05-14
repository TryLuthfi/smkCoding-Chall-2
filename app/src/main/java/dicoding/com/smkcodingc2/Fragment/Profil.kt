package dicoding.com.smkcodingc2.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import dicoding.com.smkcodingc2.R
import kotlinx.android.synthetic.main.fragment_profil.*
import kotlinx.android.synthetic.main.list_kategori.*

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
        if(idUserV != "empty" && emailV != "empty" && passwordV != "empty" && firstNameV != "empty" && lastNameV != "empty"){
            tv_username.setText(firstNameV+lastNameV)
            tv_email.setText(emailV)
        }

        Glide.with(context!!)
            .load("https://htmlcolors.com/color-image/ffffff.png").apply(requestOptions)
            .into(white_circle)


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
}
