package com.binar.projekakhir.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentSendOtpBinding
import com.binar.projekakhir.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendOtpFragment : Fragment() {

    private lateinit var binding: FragmentSendOtpBinding
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var userVm : UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSendOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVm = ViewModelProvider(this).get(UserViewModel::class.java)

        sharedPreferences = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        binding.btnVerif.setOnClickListener {
            inputOtp()
        }

        binding.tvKirimulang.setOnClickListener {
            resendotp()
        }





    }

    fun inputOtp(){
        val inputOtp = binding.pinview.text.toString()
        userVm = ViewModelProvider(this).get(UserViewModel::class.java)
        var getEmail = sharedPreferences.getString("email", "")
        userVm.verifyOtpRequest(getEmail!!,inputOtp)
        userVm.verifyOtp.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_sendOtpFragment_to_loginFragment)
        }

    }

    fun resendotp(){
        var getEmail = sharedPreferences.getString("email", "")
        userVm.resendOtpRequest(getEmail!!)
        userVm.resendOtp.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
        }
    }






}

