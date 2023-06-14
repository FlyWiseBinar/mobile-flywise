package com.binar.projekakhir.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentRiwayatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RiwayatFragment : Fragment() {
    private lateinit var binding : FragmentRiwayatBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRiwayatBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_riwayatFragment2_to_loginFragment)
        }



    }


}