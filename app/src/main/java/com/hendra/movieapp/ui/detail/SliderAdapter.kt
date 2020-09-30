package com.hendra.movieapp.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.item_pager_slider.view.*


class SliderAdapter(private val listPoster: List<String>) : SliderViewAdapter<SliderAdapterVH>() {
    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapterVH {
        return SliderAdapterVH(
            LayoutInflater
                .from(parent?.context)
                .inflate(com.hendra.movieapp.R.layout.item_pager_slider, parent, false)
        )
    }

    override fun getCount(): Int = listPoster.size

    override fun onBindViewHolder(viewHolder: SliderAdapterVH?, position: Int) {
        viewHolder?.bindData(listPoster[position])
    }

}

class SliderAdapterVH(itemView: View) :
    SliderViewAdapter.ViewHolder(itemView) {

    fun bindData(poster: String) {
        Glide.with(itemView)
            .load(poster)
            .placeholder(ColorDrawable(Color.GRAY))
            .into(itemView.posterImage)

    }
}