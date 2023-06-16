package com.binar.projekakhir.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentSendEmailOtpBinding
import com.binar.projekakhir.databinding.FragmentSendOtpBinding
import com.binar.projekakhir.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendEmailOtpFragment : Fragment() {
    private lateinit var binding: FragmentSendEmailOtpBinding
    private lateinit var userVm : UserViewModel
    lateinit var sharedRegis: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSendEmailOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVm = ViewModelProvider(this).get(UserViewModel::class.java)

        sharedRegis = requireContext().getSharedPreferences("keyUser", Context.MODE_PRIVATE)

        binding.btnSendotp.setOnClickListener{
            //menampilkan email yang dikirim ke tv halaman otp
            var getEmail = binding.etEmail.text.toString()
            var addUser = sharedRegis.edit()
            addUser.putString("name", getEmail)
            addUser.apply()
            sendOtpToEmail()
        }

    }


    private fun sendOtpToEmail() {
        val email = binding.etEmail.text.toString()

        if (email.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all the field", Toast.LENGTH_SHORT).show()
        } else {
            userVm.sendOtpRequest(email)
            userVm.responseOtp.observe(viewLifecycleOwner){
                if (it.message == "Otp sent successfully"){
                    findNavController().navigate(R.id.action_sendEmailOtpFragment_to_sendOtpFragment)
                }
            }
//            Toast.makeText(requireContext(), "Registration Success", Toast.LENGTH_SHORT).show()



        }
    }


}