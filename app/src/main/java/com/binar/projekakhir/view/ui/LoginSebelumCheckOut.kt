package com.binar.projekakhir.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentLoginSebelumCheckOutBinding
import com.binar.projekakhir.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginSebelumCheckOut : Fragment() {
    lateinit var binding: FragmentLoginSebelumCheckOutBinding
    //val loginVM: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginSebelumCheckOutBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
       return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        binding?.btnLogin?.setOnClickListener {
            findNavController().navigate(R.id.action_loginSebelumCheckOut_to_loginFragment)
        }
    }



}

