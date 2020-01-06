package com.jonathanfullola.gamecompanion.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonathanfullola.gamecompanion.R
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.item_stream.view.*
import java.util.stream.Stream

class StreamAdapter(var list: ArrayList<String>): RecyclerView.Adapter<StreamAdapter.ViewHolder>(){

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val button = itemView.button
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stream, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.button.text = list[position]

        holder.button.setOnClickListener{
            val intent = Intent()
            intent.putExtra("id", list[position])
        }
    }
}