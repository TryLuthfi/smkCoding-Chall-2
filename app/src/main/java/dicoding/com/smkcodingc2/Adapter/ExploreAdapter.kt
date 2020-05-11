package dicoding.com.smkcodingc2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dicoding.com.smkcodingc2.Konfigurasi.konfigurasi
import dicoding.com.smkcodingc2.KotlinGenerate.Wisata
import dicoding.com.smkcodingc2.KotlinGenerate.WisataItem
import dicoding.com.smkcodingc2.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_kategory.*

class ExploreAdapter(
    private val context: Context, private val items:
    List<WisataItem>, private val listener: (WisataItem) -> Unit
) :
    RecyclerView.Adapter<ExploreAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            context, LayoutInflater.from(context).inflate(
                R.layout.list_kategory,
                parent, false
            )
        )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: WisataItem, listener: (WisataItem) -> Unit) {
            tv_nama.text = item.nama
            tv_deskripsi.setText("  "+item.deskripsi)
            Glide.with(context).load(konfigurasi.REGISTRASI_URL +item.gambar).into(iv_gambar)
            containerView.setOnClickListener { listener(item) }
        }
    }
}