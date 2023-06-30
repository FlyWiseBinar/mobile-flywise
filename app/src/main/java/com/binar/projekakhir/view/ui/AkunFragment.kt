package com.binar.projekakhir.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentAkunBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AkunFragment : Fragment() {

    private lateinit var binding : FragmentAkunBinding
    private lateinit var pref : SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAkunBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.ubahprofile.setOnClickListener {
            findNavController().navigate(R.id.action_akunFragment2_to_profileFragment2)

        }
    }

//    private fun isLogin() {
//        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)
//        if (pref.getString("token", "")!!.isEmpty()) {
//            binding.
//            binding.layoutNoLoginHistori.visibility = View.GONE
//        } else {
//            binding.layoutloginHistori.visibility = View.GONE
//            binding.layoutNoLoginHistori.visibility = View.VISIBLE
//        }
//    }




}