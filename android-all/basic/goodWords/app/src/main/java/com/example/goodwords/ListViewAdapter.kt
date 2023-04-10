package com.example.goodwords

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter(private val list: MutableList<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    // Adapter에 넘어온 데이터들을 listview_item에 넣어서 하나씩 리스트뷰에 넣어주는 작업 정의
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var convertView = p1 ?: LayoutInflater.from(p2?.context).inflate(R.layout.listview_item, p2, false)
        val listViewText = convertView.findViewById<TextView>(R.id.listViewTextArea)
        listViewText.text = list[p0]

        return convertView
    }
}