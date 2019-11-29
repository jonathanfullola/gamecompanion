package com.jonathanfullola.gamecompanion.util

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jonathanfullola.gamecompanion.model.GuideModel
import kotlinx.android.synthetic.main.item_guide.view.*


class GuideListAdapter : RecyclerView.Adapter<GuideListAdapter.ViewHolder>(){

    var elements = ArrayList<GuideModel>()

    //TODO create view
    

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val title: TextView = itemView.title
}
}