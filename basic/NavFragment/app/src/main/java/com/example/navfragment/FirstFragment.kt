package com.example.navfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController

class FirstFragment : Fragment() {
    private val toastMaker = ToastMaker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val btn2 = view.findViewById<Button>(R.id.btn2)
        val btn3 = view.findViewById<Button>(R.id.btn3)

        btn2.setOnClickListener {
            it.findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
            toastMaker.makeToast(requireContext(), "first -> second")
        }

        btn3.setOnClickListener {
            it.findNavController().navigate(R.id.action_firstFragment_to_thirdFragment)
            toastMaker.makeToast(requireContext(), "first -> third")
        }

        return view
    }
}