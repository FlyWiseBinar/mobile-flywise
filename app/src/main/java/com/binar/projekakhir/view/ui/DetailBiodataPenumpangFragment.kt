package com.binar.projekakhir.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentDetailBiodataPenumpangBinding


class DetailBiodataPenumpangFragment : Fragment() {

    private lateinit var binding : FragmentDetailBiodataPenumpangBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBiodataPenumpangBinding.inflate(inflater,container,false)
        return binding.root
    }


}