package com.example.demoapplication.main.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapplication.R
import com.example.domain.models.DataModel

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var data = listOf<DataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    fun setData(newData: List<DataModel>) {
        data = newData
        notifyDataSetChanged()
    }
}

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val titleTextView: TextView

    init {
        with(itemView) {
            titleTextView = findViewById(R.id.main_item_name)
        }
    }

    fun bind(state: DataModel) {
        titleTextView.text = state.url
    }
}