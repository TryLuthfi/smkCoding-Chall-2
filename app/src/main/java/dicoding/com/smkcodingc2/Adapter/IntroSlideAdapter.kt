package dicoding.com.smkcodingc2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dicoding.com.smkcodingc2.R
import dicoding.com.smkcodingc2.Request.IntroSlide
import kotlinx.android.synthetic.main.slide_item_container.view.*

class IntroSlideAdapter(private val introSlide: List<IntroSlide>) :
    RecyclerView.Adapter<IntroSlideAdapter.IntroSlideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): IntroSlideAdapter.IntroSlideViewHolder {
        return IntroSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return introSlide.size
    }

    override fun onBindViewHolder(holder: IntroSlideAdapter.IntroSlideViewHolder, position: Int) {
        holder.bind(introSlide[position])
    }

    inner class IntroSlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)
        private val imageIcon = view.findViewById<ImageView>(R.id.imageSlideIcon)
        fun bind(introSlide: IntroSlide){
            textTitle.text = introSlide.title
            textDescription.text = introSlide.description
            imageIcon.setImageResource(introSlide.icon)
        }
    }
}