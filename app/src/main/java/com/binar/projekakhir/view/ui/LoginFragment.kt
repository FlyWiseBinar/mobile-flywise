package com.binar.projekakhir.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentLoginBinding
import com.binar.projekakhir.model.auth.Data
import com.binar.projekakhir.model.auth.LoginBody
import com.binar.projekakhir.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var userVm: UserViewModel
    private lateinit var binding: FragmentLoginBinding
    private lateinit var pref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVm = ViewModelProvider(this).get(UserViewModel::class.java)
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)



        binding.tvSignupHere.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.tvForgetPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_resetPassFragment)
        }

        binding.btnSignin.setOnClickListener {
            login()
        }

    }


    private fun login() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()


        if (email.isNotEmpty() && password.isNotEmpty()){

            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Kata sandi harus di isi", Toast.LENGTH_SHORT).show()
        }

    }


    }



