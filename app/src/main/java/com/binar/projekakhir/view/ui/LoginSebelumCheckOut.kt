package com.binar.projekakhir.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentLoginSebelumCheckOutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginSebelumCheckOut : Fragment() {
    lateinit var binding: FragmentLoginSebelumCheckOutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding?.btnLogin?.setOnClickListener {
            findNavController().navigate(R.id.action_loginSebelumCheckOut_to_loginFragment)
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_sebelum_check_out, container, false)
    }



}