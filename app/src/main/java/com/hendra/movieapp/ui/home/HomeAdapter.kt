package com.hendra.movieapp.ui.home

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hendra.movieapp.R


class HomeAdapter(private val list: List<String>) : RecyclerView.Adapter<ItemAccountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAccountViewHolder {
        return ItemAccountViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.poster_item_list, parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ItemAccountViewHolder, position: Int) {
        holder.bindData(list[position])
    }

}

class ItemAccountViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var poster: ImageView = itemView.findViewById(R.id.posterImage)

    fun bindData(item: String) {
        val res: Resources = itemView.context.resources

        try {
            val imageStream = res.assets.open(item)
            val drawable: Drawable = BitmapDrawable(res, imageStream)
            Glide.with(itemView)
                .load(drawable)
                .placeholder(ColorDrawable(Color.GRAY))
                .into(poster)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}