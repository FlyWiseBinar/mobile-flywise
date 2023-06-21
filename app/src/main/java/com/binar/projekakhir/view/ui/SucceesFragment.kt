package com.binar.projekakhir.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentSucceesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SucceesFragment : Fragment() {
    lateinit var binding : FragmentSucceesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSucceesBinding.inflate(inflater, container,false)
        return inflater.inflate(R.layout.fragment_succees, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSelectFlight.setOnClickListener{
            findNavController().navigate(R.id.action_succeesFragment_to_homeFragment2)
        }
    }


}