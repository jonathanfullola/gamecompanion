package com.jonathanfullola.gamecompanion.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jonathanfullola.gamecompanion.R
import com.jonathanfullola.gamecompanion.model.ChatMessage
import kotlinx.android.synthetic.main.item_guide.view.*

class ChatAdapter: RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    var elements = List<ChatMessage>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_guide,parent,false)
        return ChatAdapter.ViewHolder(itemView)
    }

    // Total List Items
    override fun getItemCount(): Int {
        return elements.count()
    }

    // UPDATE Item (holder: ViewHolder) at specific position (position: Int)
    override fun onBindViewHolder(holder: GuideListAdapter.ViewHolder, position: Int) {
        val element = elements[position]

        // Update Views
        holder.title.text = element.text
        holder.title.timeStamp = element.timesteamp
        holder.title.userId = element.userId

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val msg: TextView = itemView.title
    }
}


