package com.vinoth.interview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.vinoth.interview.PointsAdapter.MyViewHolder
import com.vinoth.interview.dao.Posts

class PointsAdapter(private val context: Context, private val postsList: List<Posts>) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hero = postsList[position]
        holder.textView.text = hero.title
        holder.body.text = hero.body
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.textView)
        var body: TextView = itemView.findViewById(R.id.body)
    }
}