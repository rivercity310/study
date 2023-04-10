package com.example.singer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(private val items: List<RecyclerViewModel>): RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    lateinit var itemClick: ItemClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick.onClick(it, position)
        }

        holder.bindItems(items[position])
    }

    // 전체 리사이클러뷰 개수
    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        internal fun bindItems(item: RecyclerViewModel) {
            val rvItem1 = itemView.findViewById<TextView>(R.id.rvItem)
            val rvItem2 = itemView.findViewById<TextView>(R.id.rvItem2)

            rvItem1.text = item.name
            rvItem2.text = item.age.toString()
        }
    }
}