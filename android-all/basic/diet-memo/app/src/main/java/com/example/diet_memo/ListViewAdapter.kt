package com.example.diet_memo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter(private val list : MutableList<DataModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {
        val convertView = p1 ?: LayoutInflater
            .from(parent?.context)
            .inflate(R.layout.listview_item, parent, false)

        val dateArea = convertView.findViewById<TextView>(R.id.listViewDateArea)
        val memoArea = convertView.findViewById<TextView>(R.id.listViewMemoArea)

        dateArea.text = list[position].dateText
        memoArea.text = list[position].memo

        return convertView
    }
}