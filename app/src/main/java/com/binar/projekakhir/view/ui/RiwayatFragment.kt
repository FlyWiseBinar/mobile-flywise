package com.binar.projekakhir.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
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
        return inflater.inflate(R.layout.fragment_riwayat, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}