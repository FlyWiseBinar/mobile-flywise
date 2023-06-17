package com.binar.projekakhir.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentRegisterBinding
import com.binar.projekakhir.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var userVm: UserViewModel
    private lateinit var pref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVm = ViewModelProvider(this).get(UserViewModel::class.java)
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)



        binding.tvMasukdisini.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.btnDaftar.setOnClickListener {
            register()
        }

        binding.tvMasukdisini.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }


    }

    private fun register() {
        val fullName = binding.txtInputLayoutNama.text.toString()
        val email = binding.txtInputLayoutEmail.text.toString()
        val telephone = "0" + binding.txtInputNoTelp.text.toString()
        val password = binding.txtInputLayoutPass.text.toString()


        if (fullName.isEmpty() || email.isEmpty() || telephone.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all the field", Toast.LENGTH_SHORT).show()
        } else {
//            userVm.postregist("ilham","ilham@gmail.com", "123456","0821233423121")
            userVm.postregist(fullName, email, password, telephone)

            userVm.responseRegister.observe(viewLifecycleOwner) {
                if (it.status == true) {
                    userVm.sendOtpRequest(email)
                    userVm.responseOtp.observe(viewLifecycleOwner) {
                        Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                        val sharedPref = pref.edit()
                        sharedPref.putString("email", email)
                        sharedPref.putString("telephone", telephone)
                        sharedPref.putString("fullname", fullName)
                        sharedPref.apply()
                        findNavController().navigate(R.id.action_registerFragment_to_sendOtpFragment)
                    }


                }

            }
        }


    }
}

