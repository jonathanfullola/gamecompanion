package com.jonathanfullola.gamecompanion.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jonathanfullola.gamecompanion.R
import com.jonathanfullola.gamecompanion.model.GuideModel
import kotlinx.android.synthetic.main.item_guide.view.*


class GuideListAdapter: RecyclerView.Adapter<GuideListAdapter.ViewHolder>(){

    var elements = ArrayList<GuideModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_guide,parent,false)
        return ViewHolder(itemView)
    }

    // Total List Items
    override fun getItemCount(): Int {
        return elements.count()
    }

    // UPDATE Item (holder: ViewHolder) at specific position (position: Int)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val element = elements[position]

        // Update Views
        holder.title.text = element.title
        Glide.with(holder.image.context).load(element.url).into(holder.image)
    }


class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val title: TextView = itemView.title
    val image: ImageView = itemView.imageView
}
}