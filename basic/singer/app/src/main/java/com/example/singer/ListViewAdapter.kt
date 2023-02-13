package com.example.singer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter(private val lst: MutableList<ListViewModel>): BaseAdapter() {
    override fun getCount(): Int {
        return lst.size
    }

    override fun getItem(p0: Int): Any {
        return lst[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val convertView =  p1 ?: LayoutInflater.from(p2?.context).inflate(R.layout.listview_item, p2, false)
        val title = convertView.findViewById<TextView>(R.id.listViewItemText)
        val desc = convertView.findViewById<TextView>(R.id.listViewItemText2)

        title.text = lst[p0].text1
        desc.text = lst[p0].text2

        return convertView
    }
}