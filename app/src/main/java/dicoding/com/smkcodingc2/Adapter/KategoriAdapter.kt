package dicoding.com.smkcodingc2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dicoding.com.smkcodingc2.Konfigurasi.konfigurasi
import dicoding.com.smkcodingc2.KotlinGenerate.KategoriItem
import dicoding.com.smkcodingc2.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_kategori.*

class KategoriAdapter(
    var row_index: Int = -1,
    private val context: Context,private val items:
    List<KategoriItem>, private val listener: (KategoriItem) -> Unit
) :
    RecyclerView.Adapter<KategoriAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            context, LayoutInflater.from(context).inflate(
                R.layout.list_kategori,
                parent, false
            )
        )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val requestOptions = RequestOptions()
            .placeholder(R.color.colorBlack)

        holder.itemView.setOnClickListener {
            row_index = position
            notifyDataSetChanged()
        }

        if (row_index == position) {
            Glide.with(context)
                .load("https://htmlcolors.com/color-image/2196f3.png").apply(requestOptions)
                .into(holder.gambarLuar)
        } else {
            Glide.with(context)
                .load("https://htmlcolors.com/color-image/ffffff.png").apply(requestOptions)
                .into(holder.gambarLuar)
        }
        holder.bindItem(items.get(position), listener)
    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: KategoriItem, listener: (KategoriItem) -> Unit) {
            textLayout.text = item.nama
            Glide.with(context).load(konfigurasi.KATEGORI_URL +item.gambar).into(image)
            containerView.setOnClickListener { listener(item) }
        }
    }
}