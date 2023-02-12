package com.example.customfont

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter(val list: MutableList<ListViewModel>) : BaseAdapter() {
    // 리스트뷰의 개수
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    //
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var convertView = p1

        if (convertView == null) {
            convertView = LayoutInflater
                .from(p2?.context)
                .inflate(R.layout.listview_item, p2, false)
        }

        val title = convertView?.findViewById<TextView>(R.id.listviewItem)
        val content = convertView?.findViewById<TextView>(R.id.listviewItem2)

        title?.text = list[p0].title
        content?.text = list[p0].content

        return convertView!!
    }
}