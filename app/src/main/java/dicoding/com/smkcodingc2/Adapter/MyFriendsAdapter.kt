package dicoding.com.smkcodingc2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dicoding.com.smkcodingc2.Model.mMyFriends
import dicoding.com.smkcodingc2.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_myfriend_item.*

class MyFriendAdapter(private val context: Context, private val items : ArrayList<mMyFriends>) :
    RecyclerView.Adapter<MyFriendAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.list_myfriend_item, parent, false)
    )
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position))
    }
    class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: mMyFriends) {
            name.text = item.nama
            email.text = item.email
            telp.text = item.telp
        }
    }
}