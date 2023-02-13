package com.example.navfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class ThirdFragment : Fragment() {
    private val toastMaker = ToastMaker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_third, container, false)

        view.findViewById<Button>(R.id.btn1).setOnClickListener {
            it.findNavController().navigate(R.id.action_thirdFragment_to_firstFragment)
            toastMaker.makeToast(requireContext(), "third -> first")
        }

        view.findViewById<Button>(R.id.btn2).setOnClickListener {
            it.findNavController().navigate(R.id.action_thirdFragment_to_secondFragment)
            toastMaker.makeToast(requireContext(), "third -> second")
        }

        return view
    }
}