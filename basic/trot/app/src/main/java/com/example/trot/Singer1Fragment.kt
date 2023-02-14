package com.example.trot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Singer1Fragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_singer1, container, false)

        view.findViewById<ImageView>(R.id.image2)
            .setOnClickListener {
                it.findNavController().navigate(R.id.action_singer1Fragment_to_singer2Fragment)
            }

        view.findViewById<ImageView>(R.id.image3)
            .setOnClickListener {
                it.findNavController().navigate(R.id.action_singer1Fragment_to_singer3Fragment)
            }

        val items = mutableListOf<String>()

        for (i in 1 until 100) items.add("노래$i")

        val rv = view.findViewById<RecyclerView>(R.id.singer1)
        val rvAdapter = RVAdapter(items)

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(context)

        return view
    }
}